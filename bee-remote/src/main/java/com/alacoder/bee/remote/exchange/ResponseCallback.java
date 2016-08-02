/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ResponseCallback.java
 * @Package com.alacoder.bee.remote.exchange
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午5:56:43
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange;

/**
 * @ClassName: ResponseCallback
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年8月1日 下午5:56:43
 *
 */

public interface ResponseCallback {

	void done(Object response);
	
	void caught(Throwable exception);
}
