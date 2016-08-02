/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: ProxyFactory.java
 * @Package com.alacoder.bee.rpc
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:38:44
 * @version V1.0
 */

package com.alacoder.bee.rpc;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: ProxyFactory
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午3:38:44
 *
 */
@SPI("javassist")
public interface ProxyFactory {

    @Adaptive({Constants.PROXY_KEY})
    <T> T getProxy(Invoker<T> invoker) throws RpcException;
	
    @Adaptive({Constants.PROXY_KEY})
	<T> Invoker<T> getInvoker(T proxy, Class<T> type ,URL url) throws RpcException;
	
}
