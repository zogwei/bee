/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: InvokerListener.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:35:03
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: InvokerListener
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:35:03
 *
 */
@SPI
public interface InvokerListener {

	void referred(Invoker<?> invoker) throws RpcException;
	
	void destroyed(Invoker<?> invoker) ;
}
