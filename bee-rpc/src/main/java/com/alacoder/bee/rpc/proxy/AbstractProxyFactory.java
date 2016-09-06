/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: AbstractProxyFactory.java
 * @Package com.alacoder.bee.rpc.proxy
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:05:20
 * @version V1.0
 */

package com.alacoder.bee.rpc.proxy;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.utils.ReflectUtils;
import com.alacoder.bee.rpc.Invoker;
import com.alacoder.bee.rpc.ProxyFactory;
import com.alacoder.bee.rpc.RpcException;
import com.alacoder.bee.rpc.service.EchoService;

/**
 * @ClassName: AbstractProxyFactory
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:05:20
 *
 */

public abstract class AbstractProxyFactory implements ProxyFactory {

	public <T> T getProxy(Invoker<T> invoker) throws RpcException {
        Class<?>[] interfaces = null;
        String config = invoker.getUrl().getParameter("interfaces");
        if (config != null && config.length() > 0) {
            String[] types = Constants.COMMA_SPLIT_PATTERN.split(config);
            if (types != null && types.length > 0) {
                interfaces = new Class<?>[types.length + 2];
                interfaces[0] = invoker.getInterface();
                interfaces[1] = EchoService.class;
                for (int i = 0; i < types.length; i ++) {
                    interfaces[i + 1] = ReflectUtils.forName(types[i]);
                }
            }
        }
        if (interfaces == null) {
            interfaces = new Class<?>[] {invoker.getInterface(), EchoService.class};
        }
        return getProxy(invoker, interfaces);
    }
    
    public abstract <T> T getProxy(Invoker<T> invoker, Class<?>[] types);

}