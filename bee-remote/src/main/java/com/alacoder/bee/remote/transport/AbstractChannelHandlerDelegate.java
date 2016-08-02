/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: AbstractChannelHandlerDelegate.java
 * @Package com.alacoder.bee.remote.transport
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:13:36
 * @version V1.0
 */

package com.alacoder.bee.remote.transport;

import com.alacoder.bee.common.utils.Assert;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: AbstractChannelHandlerDelegate
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:13:36
 *
 */

public abstract class AbstractChannelHandlerDelegate implements ChannelHandlerDelegate {

    protected ChannelHandler handler;

    protected AbstractChannelHandlerDelegate(ChannelHandler handler) {
        Assert.notNull(handler, "handler == null");
        this.handler = handler;
    }

    public ChannelHandler getHandler() {
        if (handler instanceof ChannelHandlerDelegate) {
            return ((ChannelHandlerDelegate)handler).getHandler();
        }
        return handler;
    }

    public void connected(Channel channel) throws RemotingException {
        handler.connected(channel);
    }

    public void disconnected(Channel channel) throws RemotingException {
        handler.disconnected(channel);
    }

    public void sent(Channel channel, Object message) throws RemotingException {
        handler.sent(channel, message);
    }

    public void received(Channel channel, Object message) throws RemotingException {
        handler.received(channel, message);
    }

    public void caught(Channel channel, Throwable exception) throws RemotingException {
        handler.caught(channel, exception);
    }
}
