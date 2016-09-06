/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Exchanger.java
 * @Package com.alacoder.bee.remote.exchange
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月2日 下午3:56:18
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;
import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: Exchanger
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月2日 下午3:56:18
 *
 */
@SPI(HeaderExchanger.NAME)
public interface Exchanger {

	@Adaptive({Constants.EXCHANGER_KEY})
	ExchangeServer bind(URL url, ExchangeHandler handler) throws RemotingException;
	
	@Adaptive({Constants.EXCHANGER_KEY})
	ExchangeClient connect(URL url, ExchangeHandler handler) throws RemotingException;
}
