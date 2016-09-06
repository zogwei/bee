/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: TelnetHandler.java
 * @Package com.alacoder.bee.remote.telnet
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午6:04:24
 * @version V1.0
 */

package com.alacoder.bee.remote.telnet;

import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: TelnetHandler
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午6:04:24
 *
 */

public interface TelnetHandler {

	String telnet(Channel channel, String message) throws RemotingException;
}
