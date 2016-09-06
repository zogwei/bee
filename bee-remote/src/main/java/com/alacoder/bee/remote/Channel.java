/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Channel.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:15:02
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.net.InetSocketAddress;

/**
 * @ClassName: Channel
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午4:15:02
 *
 */

public interface Channel extends Endpoint {

	InetSocketAddress getRemoteAddress();
	
	boolean isConnected();
	
	boolean hasAttribute(String key);
	
	Object getAttribute(String key);
	
	void setAttribute(String key, Object value);
	
    void removeAttribute(String key);
}
