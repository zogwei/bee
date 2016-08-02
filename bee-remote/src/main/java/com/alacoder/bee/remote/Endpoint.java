/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Endpoint.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:07:53
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.net.InetSocketAddress;

import com.alacoder.bee.common.URL;

/**
 * @ClassName: Endpoint
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:07:53
 *
 */

public interface Endpoint {

	URL getUrl();
	
	ChannelHandler getChannelHandler();
	
	InetSocketAddress getLocalAddress();
	
	void send(Object message) throws RemotingException;
	
	void send(Object message, boolean sent) throws RemotingException;
	
	void close();
	
	void close(int timeout);
	
	boolean isClosed();
	
}
