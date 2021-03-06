/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: Invoker.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午2:45:24
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import com.alacoder.bee.common.Node;

/**
 * @ClassName: Invoker
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午2:45:24
 *
 */

public interface Invoker<T> extends Node {

	Class<T> getInterface();
	
	Result invoke(Invocation invocation) throws RpcException;
}
