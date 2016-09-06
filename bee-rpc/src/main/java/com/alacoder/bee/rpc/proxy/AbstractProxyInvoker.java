/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: AbstractProxyInvoker.java
 * @Package com.alacoder.bee.rpc.proxy
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:19:06
 * @version V1.0
 */

package com.alacoder.bee.rpc.proxy;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.rpc.Invocation;
import com.alacoder.bee.rpc.Invoker;
import com.alacoder.bee.rpc.Result;
import com.alacoder.bee.rpc.RpcException;
import com.alacoder.bee.rpc.RpcResult;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName: AbstractProxyInvoker
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:19:06
 *
 */

public abstract class AbstractProxyInvoker<T> implements Invoker<T> {
    
    private final T proxy;
    
    private final Class<T> type;
    
    private final URL url;

    public AbstractProxyInvoker(T proxy, Class<T> type, URL url){
        if (proxy == null) {
            throw new IllegalArgumentException("proxy == null");
        }
        if (type == null) {
            throw new IllegalArgumentException("interface == null");
        }
        if (! type.isInstance(proxy)) {
            throw new IllegalArgumentException(proxy.getClass().getName() + " not implement interface " + type);
        }
        this.proxy = proxy;
        this.type = type;
        this.url = url;
    }

    public Class<T> getInterface() {
        return type;
    }

    public URL getUrl() {
        return url;
    }

    public boolean isAvailable() {
        return true;
    }

    public void destroy() {
    }

    public Result invoke(Invocation invocation) throws RpcException {
        try {
            return new RpcResult(doInvoke(proxy, invocation.getMethodName(), invocation.getParameterTypes(), invocation.getArguments()));
        } catch (InvocationTargetException e) {
            return new RpcResult(e.getTargetException());
        } catch (Throwable e) {
            throw new RpcException("Failed to invoke remote proxy method " + invocation.getMethodName() + " to " + getUrl() + ", cause: " + e.getMessage(), e);
        }
    }
    
    protected abstract Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable;

    @Override
    public String toString() {
        return getInterface() + " -> " + getUrl()==null?" ":getUrl().toString();
    }

    
}