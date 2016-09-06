/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: HeapChannelBufferFactory.java
 * @Package com.alacoder.bee.remote.buffer
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午4:03:16
 * @version V1.0
 */

package com.alacoder.bee.remote.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: HeapChannelBufferFactory
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午4:03:16
 *
 */

public class HeapChannelBufferFactory implements ChannelBufferFactory{

    private static final HeapChannelBufferFactory INSTANCE = new HeapChannelBufferFactory();

    public static ChannelBufferFactory getInstance() {
        return INSTANCE;
    }

    public HeapChannelBufferFactory() {
        super();
    }

    public ChannelBuffer getBuffer(int capacity) {
        return ChannelBuffers.buffer(capacity);
    }

    public ChannelBuffer getBuffer(byte[] array, int offset, int length) {
        return ChannelBuffers.wrappedBuffer(array, offset, length);
    }

    public ChannelBuffer getBuffer(ByteBuffer nioBuffer) {
        if (nioBuffer.hasArray()) {
            return ChannelBuffers.wrappedBuffer(nioBuffer);
        }

        ChannelBuffer buf = getBuffer(nioBuffer.remaining());
        int pos = nioBuffer.position();
        buf.writeBytes(nioBuffer);
        nioBuffer.position(pos);
        return buf;
    }

}
