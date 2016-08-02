/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Client.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:40:31
 * @version V1.0
 */

package com.alacoder.bee.remote;

import com.alacoder.bee.common.Resetable;

/**
 * @ClassName: Client
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:40:31
 *
 */

public interface Client  extends Endpoint, Channel, Resetable {

    /**
     * reconnect.
     */
    void reconnect() throws RemotingException;
    
}