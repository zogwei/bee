/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: Codec2.java
 * @Package com.alacoder.bee.remote
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:41:57
 * @version V1.0
 */

package com.alacoder.bee.remote;

import java.io.IOException;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.extension.Adaptive;
import com.alacoder.bee.common.extension.SPI;
import com.alacoder.bee.remote.buffer.ChannelBuffer;

/**
 * @ClassName: Codec2
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:41:57
 *
 */
@SPI
public interface Codec2 {

	@Adaptive({Constants.CODEC_KEY})
    void encode(Channel channel, ChannelBuffer buffer, Object message) throws IOException;

    @Adaptive({Constants.CODEC_KEY})
    Object decode(Channel channel, ChannelBuffer buffer) throws IOException;


    enum DecodeResult {
        NEED_MORE_INPUT, SKIP_SOME_INPUT
    }

}
