/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: AbstractChannel.java
 * @Package com.alacoder.bee.remote.transport
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午1:29:35
 * @version V1.0
 */

package com.alacoder.bee.remote.transport;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: AbstractChannel
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午1:29:35
 *
 */

public abstract class AbstractChannel extends AbstractPeer implements Channel {
	
	public AbstractChannel(URL url, ChannelHandler handler) {
		super(url, handler);
	}

	public void send(Object message, boolean sent) throws RemotingException {
		 if(isClosed()) {
			 throw new RemotingException(this, "Failed to send message "
                     + (message == null ? "" : message.getClass().getName()) + ":" + message
                     + ", cause: Channel closed. channel: " + getLocalAddress() + " -> " + getRemoteAddress());
		 }
	}
	
	public String toString() {
		return getLocalAddress() + "->" + getRemoteAddress();
	}
	
}
