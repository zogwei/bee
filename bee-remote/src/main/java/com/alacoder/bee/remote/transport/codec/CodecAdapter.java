/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: CodecAdapter.java
 * @Package com.alacoder.bee.remote.transport.codec
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:07:12
 * @version V1.0
 */

package com.alacoder.bee.remote.transport.codec;

import java.io.IOException;

import com.alacoder.bee.common.io.UnsafeByteArrayInputStream;
import com.alacoder.bee.common.io.UnsafeByteArrayOutputStream;
import com.alacoder.bee.common.utils.Assert;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.Codec;
import com.alacoder.bee.remote.Codec2;
import com.alacoder.bee.remote.buffer.ChannelBuffer;

/**
 * @ClassName: CodecAdapter
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:07:12
 *
 */

public class CodecAdapter implements Codec2 {

	private Codec codec;
    
    public CodecAdapter(Codec codec) {
        Assert.notNull(codec, "codec == null");
        this.codec = codec;
    }

    @Override
	public void encode(Channel channel, ChannelBuffer buffer, Object message)
			throws IOException {
        UnsafeByteArrayOutputStream os = new UnsafeByteArrayOutputStream(1024);
        codec.encode(channel, os, message);
        buffer.writeBytes(os.toByteArray());
		
	}

    public Object decode(Channel channel, ChannelBuffer buffer) throws IOException {
        byte[] bytes = new byte[buffer.readableBytes()];
        int savedReaderIndex = buffer.readerIndex();
        buffer.readBytes(bytes);
        UnsafeByteArrayInputStream is = new UnsafeByteArrayInputStream(bytes);
        Object result = codec.decode(channel, is);
        buffer.readerIndex(savedReaderIndex + is.position());
        return result == Codec.NEED_MORE_INPUT ? DecodeResult.NEED_MORE_INPUT : result;
    }
    
    public Codec getCodec() {
        return codec;
    }
}
