/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: TimeoutException.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 上午10:01:13
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.net.InetSocketAddress;

/**
 * @ClassName: TimeoutException
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 上午10:01:13
 *
 */

public class TimeoutException extends RemotingException {

    private static final long serialVersionUID = 3122966731958222692L;
    
    public static final int CLIENT_SIDE = 0;
    
    public static final int SERVER_SIDE = 1;

    private final int       phase;

    public TimeoutException(boolean serverSide, Channel channel, String message){
        super(channel, message);
        this.phase = serverSide ? SERVER_SIDE : CLIENT_SIDE;
    }

    public TimeoutException(boolean serverSide, InetSocketAddress localAddress, 
                            InetSocketAddress remoteAddress, String message) {
        super(localAddress, remoteAddress, message);
        this.phase = serverSide ? SERVER_SIDE : CLIENT_SIDE;
    }

    public int getPhase() {
        return phase;
    }

    public boolean isServerSide() {
        return phase == 1;
    }

    public boolean isClientSide() {
        return phase == 0;
    }

}
