/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ChannelHandlerDelegate.java
 * @Package com.alacoder.bee.remote.transport
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:33:04
 * @version V1.0
 */

package com.alacoder.bee.remote.transport;

import com.alacoder.bee.remote.ChannelHandler;

/**
 * @ClassName: ChannelHandlerDelegate
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:33:04
 *
 */

public interface ChannelHandlerDelegate extends ChannelHandler {

	public ChannelHandler getHandler();
}
