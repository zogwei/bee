/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ChannelBufferFactory.java
 * @Package com.alacoder.bee.remote.buffer
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:45:40
 * @version V1.0
 */

package com.alacoder.bee.remote.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: ChannelBufferFactory
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月28日 下午5:45:40
 *
 */

public interface ChannelBufferFactory {

    ChannelBuffer getBuffer(int capacity);
    
    ChannelBuffer getBuffer(byte[] array, int offset, int length);
    
    ChannelBuffer getBuffer(ByteBuffer nioBuffer);
    
}
