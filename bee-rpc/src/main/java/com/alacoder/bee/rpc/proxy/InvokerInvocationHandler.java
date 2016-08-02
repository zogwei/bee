/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: InvokerInvocationHandler.java
 * @Package com.alacoder.bee.rpc.proxy
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:20:44
 * @version V1.0
 */

package com.alacoder.bee.rpc.proxy;

import com.alacoder.bee.rpc.Invoker;
import com.alacoder.bee.rpc.RpcInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: InvokerInvocationHandler
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:20:44
 *
 */

public class InvokerInvocationHandler implements InvocationHandler {

    private final Invoker<?> invoker;
    
    public InvokerInvocationHandler(Invoker<?> handler){
        this.invoker = handler;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(invoker, args);
        }
        if ("toString".equals(methodName) && parameterTypes.length == 0) {
            return invoker.toString();
        }
        if ("hashCode".equals(methodName) && parameterTypes.length == 0) {
            return invoker.hashCode();
        }
        if ("equals".equals(methodName) && parameterTypes.length == 1) {
            return invoker.equals(args[0]);
        }
        return invoker.invoke(new RpcInvocation(method, args)).recreate();
    }

}