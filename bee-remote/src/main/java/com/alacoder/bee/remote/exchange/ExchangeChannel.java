/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ExchangeChannel.java
 * @Package com.alacoder.bee.remote.exchange
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午5:50:17
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange;

import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: ExchangeChannel
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年8月1日 下午5:50:17
 *
 */

public interface ExchangeChannel extends Channel{

	ResponseFuture request(Object request) throws RemotingException;
	
	ResponseFuture request(Object request, int timeout) throws RemotingException;
	
	
}
