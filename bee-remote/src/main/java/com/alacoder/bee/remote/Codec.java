/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Codec.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:10:15
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;

/**
 * @ClassName: Codec
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:10:15
 *
 */
@Deprecated
@SPI
public interface Codec {

	Object NEED_MORE_INPUT = new Object();
	
	@Adaptive({Constants.CODEC_KEY})
    void encode(Channel channel, OutputStream output, Object message) throws IOException;
	
    @Adaptive({Constants.CODEC_KEY})
	Object decode(Channel channel, InputStream input) throws IOException;
}
