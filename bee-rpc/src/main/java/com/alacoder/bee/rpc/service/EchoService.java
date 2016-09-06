/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-rpc
 * @Title: EchoService.java
 * @Package com.alacoder.bee.rpc.service
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:06:19
 * @version V1.0
 */

package com.alacoder.bee.rpc.service;

/**
 * @ClassName: EchoService
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午2:06:19
 *
 */

public interface EchoService {

    Object $echo(Object message);

}