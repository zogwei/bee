/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: ChannelHandlers.java
 * @Package com.alacoder.bee.remote.transport.dispatcher
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午5:34:55
 * @version V1.0
 */

package com.alacoder.bee.remote.transport.dispatcher;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.extension.ExtensionLoader;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.Dispatcher;
import com.alacoder.bee.remote.exchange.support.header.HeartbeatHandler;
import com.alacoder.bee.remote.transport.MultiMessageHandler;

/**
 * @ClassName: ChannelHandlers
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午5:34:55
 *
 */

public class ChannelHandlers {

	public static ChannelHandler wrap(ChannelHandler handler, URL url) {
		return ChannelHandlers.getInstance().wrapInternal(handler, url);
	}
	
    protected ChannelHandler wrapInternal(ChannelHandler handler, URL url) {
        return new MultiMessageHandler(new HeartbeatHandler(ExtensionLoader.getExtensionLoader(Dispatcher.class)
                                        .getAdaptiveExtension().dispatch(handler, url)));
    }
	
	protected ChannelHandlers() {}
	
	private static ChannelHandlers INSTANCE = new ChannelHandlers();
	
	protected static ChannelHandlers getInstance(){
		return INSTANCE;
	}
	
    static void setTestingChannelHandlers(ChannelHandlers instance) {
        INSTANCE = instance;
    }
	
}
