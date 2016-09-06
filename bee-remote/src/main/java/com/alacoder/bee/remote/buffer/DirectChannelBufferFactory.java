/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: DirectChannelBufferFactory.java
 * @Package com.alacoder.bee.remote.buffer
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午4:05:27
 * @version V1.0
 */

package com.alacoder.bee.remote.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: DirectChannelBufferFactory
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午4:05:28
 *
 */
public class DirectChannelBufferFactory implements ChannelBufferFactory {

    private static final DirectChannelBufferFactory INSTANCE = new DirectChannelBufferFactory();

    public static ChannelBufferFactory getInstance() {
        return INSTANCE;
    }

    public DirectChannelBufferFactory() {
        super();
    }

    public ChannelBuffer getBuffer(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        if (capacity == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        return ChannelBuffers.directBuffer(capacity);
    }

    public ChannelBuffer getBuffer(byte[] array, int offset, int length) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        if (offset < 0) {
            throw new IndexOutOfBoundsException("offset: " + offset);
        }
        if (length == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        if (offset + length > array.length) {
            throw new IndexOutOfBoundsException("length: " + length);
        }

        ChannelBuffer buf = getBuffer(length);
        buf.writeBytes(array, offset, length);
        return buf;
    }

    public ChannelBuffer getBuffer(ByteBuffer nioBuffer) {
        if (!nioBuffer.isReadOnly() && nioBuffer.isDirect()) {
            return ChannelBuffers.wrappedBuffer(nioBuffer);
        }

        ChannelBuffer buf = getBuffer(nioBuffer.remaining());
        int pos = nioBuffer.position();
        buf.writeBytes(nioBuffer);
        nioBuffer.position(pos);
        return buf;
    }

}
