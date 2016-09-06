/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: HeartbeatHandler.java
 * @Package com.alacoder.bee.remote.exchange.support.header
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:18:36
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange.support.header;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.RemotingException;
import com.alacoder.bee.remote.exchange.Request;
import com.alacoder.bee.remote.exchange.Response;
import com.alacoder.bee.remote.transport.AbstractChannelHandlerDelegate;

/**
 * @ClassName: HeartbeatHandler
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:18:36
 *
 */

public class HeartbeatHandler extends AbstractChannelHandlerDelegate {

    private static final Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

    public static String KEY_READ_TIMESTAMP = "READ_TIMESTAMP";

    public static String KEY_WRITE_TIMESTAMP = "WRITE_TIMESTAMP";

    public HeartbeatHandler(ChannelHandler handler) {
        super(handler);
    }

    public void connected(Channel channel) throws RemotingException {
        setReadTimestamp(channel);
        setWriteTimestamp(channel);
        handler.connected(channel);
    }

    public void disconnected(Channel channel) throws RemotingException {
        clearReadTimestamp(channel);
        clearWriteTimestamp(channel);
        handler.disconnected(channel);
    }

    public void sent(Channel channel, Object message) throws RemotingException {
        setWriteTimestamp(channel);
        handler.sent(channel, message);
    }

    public void received(Channel channel, Object message) throws RemotingException {
        setReadTimestamp(channel);
        if (isHeartbeatRequest(message)) {
            Request req = (Request) message;
            if (req.isTwoWay()) {
                Response res = new Response(req.getId(), req.getVersion());
                res.setEvent(Response.HEARTBEAT_EVENT);
                channel.send(res);
                if (logger.isInfoEnabled()) {
                    int heartbeat = channel.getUrl().getParameter(Constants.HEARTBEAT_KEY, 0);
                    if(logger.isDebugEnabled()) {
                        logger.debug("Received heartbeat from remote channel " + channel.getRemoteAddress()
                                        + ", cause: The channel has no data-transmission exceeds a heartbeat period"
                                        + (heartbeat > 0 ? ": " + heartbeat + "ms" : ""));
                    }
	            }
            }
            return;
        }
        if (isHeartbeatResponse(message)) {
            if (logger.isDebugEnabled()) {
            	logger.debug(
                    new StringBuilder(32)
                        .append("Receive heartbeat response in thread ")
                        .append(Thread.currentThread().getName())
                        .toString());
            }
            return;
        }
        handler.received(channel, message);
    }

    private void setReadTimestamp(Channel channel) {
        channel.setAttribute(KEY_READ_TIMESTAMP, System.currentTimeMillis());
    }

    private void setWriteTimestamp(Channel channel) {
        channel.setAttribute(KEY_WRITE_TIMESTAMP, System.currentTimeMillis());
    }

    private void clearReadTimestamp(Channel channel) {
        channel.removeAttribute(KEY_READ_TIMESTAMP);
    }

    private void clearWriteTimestamp(Channel channel) {
        channel.removeAttribute(KEY_WRITE_TIMESTAMP);
    }

    private boolean isHeartbeatRequest(Object message) {
        return message instanceof Request && ((Request) message).isHeartbeat();
    }

    private boolean isHeartbeatResponse(Object message) {
        return message instanceof Response && ((Response)message).isHeartbeat();
    }
}

