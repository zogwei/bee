/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Server.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 上午9:58:55
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.net.InetSocketAddress;
import java.util.Collection;

import com.alacoder.bee.common.Resetable;

/**
 * @ClassName: Server
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 上午9:58:55
 *
 */

public interface Server extends Endpoint, Resetable{

	boolean isBound();
	
	Collection<Channel> getChannels();
	
	Channel getChannel(InetSocketAddress remoteAddress);
	
	
}
