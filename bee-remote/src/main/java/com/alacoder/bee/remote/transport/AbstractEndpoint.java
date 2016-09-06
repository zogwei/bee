/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: AbstractEndpoint.java
 * @Package com.alacoder.bee.remote.transport
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:45:43
 * @version V1.0
 */

package com.alacoder.bee.remote.transport;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.Resetable;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.ExtensionLoader;
import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.Codec;
import com.alacoder.bee.remote.Codec2;
import com.alacoder.bee.remote.transport.codec.CodecAdapter;

/**
 * @ClassName: AbstractEndpoint
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:45:43
 *
 */

public abstract class AbstractEndpoint extends AbstractPeer implements Resetable{
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractEndpoint.class);
	
	private Codec2 codec;
	
	private int timeout;
	
	private int connectTimeout;

	public AbstractEndpoint(URL url, ChannelHandler handler) {
		super(url, handler);
		this.codec = getChannelCodec(url);
		this.timeout = url.getPositiveParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
		this.connectTimeout = url.getPositiveParameter(Constants.CONNECT_TIMEOUT_KEY, Constants.DEFAULT_CONNECT_TIMEOUT);
	}
	
    public void reset(URL url) {
        if (isClosed()) {
            throw new IllegalStateException("Failed to reset parameters "
                                        + url + ", cause: Channel closed. channel: " + getLocalAddress());
        }
        try {
            if (url.hasParameter(Constants.HEARTBEAT_KEY)) {
                int t = url.getParameter(Constants.TIMEOUT_KEY, 0);
                if (t > 0) {
                    this.timeout = t;
                }
            }
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
        try {
            if (url.hasParameter(Constants.CONNECT_TIMEOUT_KEY)) {
                int t = url.getParameter(Constants.CONNECT_TIMEOUT_KEY, 0);
                if (t > 0) {
                    this.connectTimeout = t;
                }
            }
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
        try {
            if (url.hasParameter(Constants.CODEC_KEY)) {
                this.codec = getChannelCodec(url);
            }
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
    }
    
    
//    @Deprecated
//    public void reset(Parameters parameters){
//        reset(getUrl().addParameters(parameters.getParameters()));
//    }

    protected Codec2 getCodec() {
        return codec;
    }

    protected int getTimeout() {
        return timeout;
    }

    protected int getConnectTimeout() {
        return connectTimeout;
    }
	
	protected static Codec2 getChannelCodec(URL url) {
		String codecName = url.getParameter(Constants.CODEC_KEY,"telnet");
		if(ExtensionLoader.getExtensionLoader(Codec2.class).hasExtension(codecName)) {
			return ExtensionLoader.getExtensionLoader(Codec2.class).getExtension(codecName);
		} else {
            return new CodecAdapter(ExtensionLoader.getExtensionLoader(Codec.class).getExtension(codecName));
		}
	}
	
}
