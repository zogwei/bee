/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: Protocol.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:19:06
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: Protocol
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:19:06
 *
 */
@SPI("bee")
public interface Protocol<T> {

	int getDefaultPort();
	
	@Adaptive
	<T> Exporter<T> export(Invoker<T> invoker) throws RpcException;
	
    @Adaptive
	<T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;
}
