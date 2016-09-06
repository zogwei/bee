/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Transporter.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 上午11:33:38
 * @version V1.0
 */

package com.alacoder.bee.remote;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: Transporter
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 上午11:33:38
 *
 */
@SPI("netty")
public interface Transporter {

	@Adaptive({Constants.SERVER_KEY, Constants.TRANSPORTER_KEY})
	Server bind(URL url, ChannelHandler handler) throws RemotingException;
	
	@Adaptive({Constants.CLIENT_KEY, Constants.TRANSPORTER_KEY})
	Client connect(URL url, ChannelHandler handler) throws RemotingException;
}
