/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Dispatcher.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:47:47
 * @version V1.0
 */

package com.alacoder.bee.remote;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: Dispatcher
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:47:47
 *
 */
//@SPI(AllDispatcher.NAME)
public interface Dispatcher {

	 @Adaptive({Constants.DISPATCHER_KEY, "dispather", "channel.handler"}) // 后两个参数为兼容旧配置
	ChannelHandler dispatch(ChannelHandler handler, URL url);
}
