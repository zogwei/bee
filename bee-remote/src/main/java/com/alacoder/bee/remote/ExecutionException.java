/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ExecutionException.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:48:31
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.net.InetSocketAddress;

/**
 * @ClassName: ExecutionException
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:48:31
 *
 */

public class ExecutionException  extends RemotingException {
    
    private static final long serialVersionUID = -2531085236111056860L;
    
    private final Object request;

    public ExecutionException(Object request, Channel channel, String message, Throwable cause){
        super(channel, message, cause);
        this.request = request;
    }

    public ExecutionException(Object request, Channel channel, String msg){
        super(channel, msg);
        this.request = request;
    }

    public ExecutionException(Object request, Channel channel, Throwable cause){
        super(channel, cause);
        this.request = request;
    }

    public ExecutionException(Object request, InetSocketAddress localAddress, InetSocketAddress remoteAddress, String message,
                            Throwable cause){
        super(localAddress, remoteAddress, message, cause);
        this.request = request;
    }

    public ExecutionException(Object request, InetSocketAddress localAddress, InetSocketAddress remoteAddress, String message){
        super(localAddress, remoteAddress, message);
        this.request = request;
    }

    public ExecutionException(Object request, InetSocketAddress localAddress, InetSocketAddress remoteAddress, Throwable cause){
        super(localAddress, remoteAddress, cause);
        this.request = request;
    }

    
    public Object getRequest() {
        return request;
    }

}
