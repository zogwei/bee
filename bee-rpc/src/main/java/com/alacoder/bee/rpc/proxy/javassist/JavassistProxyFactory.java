/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: JavassistProxyFactory.java
 * @Package com.alacoder.bee.rpc.proxy.javassist
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:12:15
 * @version V1.0
 */

package com.alacoder.bee.rpc.proxy.javassist;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.bytecode.Proxy;
import com.alacoder.bee.common.bytecode.Wrapper;
import com.alacoder.bee.rpc.Invoker;
import com.alacoder.bee.rpc.proxy.AbstractProxyFactory;
import com.alacoder.bee.rpc.proxy.AbstractProxyInvoker;
import com.alacoder.bee.rpc.proxy.InvokerInvocationHandler;

/**
 * @ClassName: JavassistProxyFactory
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:12:15
 *
 */

public class JavassistProxyFactory extends AbstractProxyFactory {

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.getProxy(interfaces).newInstance(new InvokerInvocationHandler(invoker));
    }

    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) {
        // TODO Wrapper类不能正确处理带$的类名
        final Wrapper wrapper = Wrapper.getWrapper(proxy.getClass().getName().indexOf('$') < 0 ? proxy.getClass() : type);
        return new AbstractProxyInvoker<T>(proxy, type, url) {
            @Override
            protected Object doInvoke(T proxy, String methodName, 
                                      Class<?>[] parameterTypes, 
                                      Object[] arguments) throws Throwable {
                return wrapper.invokeMethod(proxy, methodName, parameterTypes, arguments);
            }
        };
    }

}