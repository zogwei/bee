/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: MultiMessageHandler.java
 * @Package com.alacoder.bee.remote.transport
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:12:44
 * @version V1.0
 */

package com.alacoder.bee.remote.transport;

import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.RemotingException;
import com.alacoder.bee.remote.exchange.support.MultiMessage;

/**
 * @ClassName: MultiMessageHandler
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:12:44
 *
 */

public class MultiMessageHandler extends AbstractChannelHandlerDelegate {

    public MultiMessageHandler(ChannelHandler handler) {
        super(handler);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void received(Channel channel, Object message) throws RemotingException {
        if (message instanceof MultiMessage) {
            MultiMessage list = (MultiMessage)message;
            for(Object obj : list) {
                handler.received(channel, obj);
            }
        } else {
            handler.received(channel, message);
        }
    }
}
