/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: Filter.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:33:41
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: Filter
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:33:41
 *
 */
@SPI
public interface Filter {

	Result invoker(Invoker<?> invoker, Invocation invocation) throws RpcException;
}
