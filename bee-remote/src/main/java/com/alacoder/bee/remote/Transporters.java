/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Transporters.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 上午11:37:45
 * @version V1.0
 */

package com.alacoder.bee.remote;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.ExtensionLoader;
import com.alacoder.bee.remote.transport.ChannelHandlerDispatcher;

/**
 * @ClassName: Transporters
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 上午11:37:45
 *
 */

public class Transporters {
	
	public static Server bing(String url, ChannelHandler... handlers) throws RemotingException {
		return bind(URL.valueOf(url), handlers);
	}
	
	public static Server bind(URL url, ChannelHandler... handlers) throws RemotingException {
		if (url == null) {
			throw new IllegalArgumentException("url == null ") ;
		}
		if (handlers == null || handlers.length == 0) {
			throw new IllegalArgumentException(" handlers == null ");
		}
		ChannelHandler handler;
		if(handlers.length == 1){
			handler = handlers[0];
		}
		else {
			handler = new ChannelHandlerDispatcher(handlers);
		}
		return getTransporter().bind(url, handler);
	}
	
	public static Transporter getTransporter() {
		return ExtensionLoader.getExtensionLoader(Transporter.class).getAdaptiveExtension();
	}

}
