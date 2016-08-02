/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ResponseFuture.java
 * @Package com.alacoder.bee.remote.exchange
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午5:50:59
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange;

import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: ResponseFuture
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年8月1日 下午5:50:59
 *
 */

public interface ResponseFuture {

	Object get() throws RemotingException;
	
	Object get(int timeoutInMillis) throws RemotingException;
	
	void setCallback(ResponseCallback callbakc);
	
	boolean isDone();
	
}
