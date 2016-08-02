/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ChannelHandler.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:09:04
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.net.InetSocketAddress;

import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: ChannelHandler
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:09:04
 *
 */
@SPI
public interface ChannelHandler {

	void connected(Channel channel) throws RemotingException;
	
	void disconnected(Channel channel) throws RemotingException;
	
	void sent(Channel channel, Object message) throws RemotingException;
	
	void received(Channel channel, Object message) throws RemotingException;
	
	void caught(Channel channel, Throwable exception) throws RemotingException;
}
