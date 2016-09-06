/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote-netty
 * @Title: NettyServer.java
 * @Package com.alacoder.bee.remote.netty
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:18:42
 * @version V1.0
 */

package com.alacoder.bee.remote.netty;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.utils.ExecutorUtil;
import com.alacoder.bee.common.utils.NamedThreadFactory;
import com.alacoder.bee.common.utils.NetUtils;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.RemotingException;
import com.alacoder.bee.remote.Server;
import com.alacoder.bee.remote.transport.AbstractServer;
import com.alacoder.bee.remote.transport.dispatcher.ChannelHandlers;

/**
 * @ClassName: NettyServer
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:18:42
 *
 */

public class NettyServer extends AbstractServer implements Server{

	private static final Logger logger = Logger.getLogger(NettyServer.class);
	
	private Map<String, Channel> channels;
	
	private ServerBootstrap bootstrap;
	
	private org.jboss.netty.channel.Channel channel;
	
	public NettyServer(URL url, ChannelHandler handler) throws RemotingException {
		super(url, ChannelHandlers.wrap(handler, ExecutorUtil.setThreadName(url, SERVER_THREAD_POOL_NAME)));
	}
	
	protected void doOpen() throws Throwable {
//		NettyHelper.setNettyLoggerFactroy();
		ExecutorService boss = Executors.newCachedThreadPool(new NamedThreadFactory("NettyServerBoss",true));
		ExecutorService worker = Executors.newCachedThreadPool(new NamedThreadFactory("NettyServerWorker",true));
		ChannelFactory channelFactory = new NioServerSocketChannelFactory(boss, worker ,getUrl().getPositiveParameter(Constants.IO_THREADS_KEY, Constants.DEFAULT_IO_THREADS));
		
		bootstrap = new ServerBootstrap(channelFactory);
		
		final NettyHandler nettyHandler = new NettyHandler(getUrl(), this);
		channels = nettyHandler.getChannels();
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				NettyCodecAdapter adpter = new NettyCodecAdapter(getCodec(), getUrl(), NettyServer.this);
				ChannelPipeline pipeline = Channels.pipeline();
				
				pipeline.addLast("decoder", adpter.getDecoder());
				pipeline.addLast("encode", adpter.getEncoder());
				pipeline.addLast("handler", nettyHandler);
				
				return pipeline;
			}
		});
		
		channel = bootstrap.bind(getBindAddress());
	}
	
	protected void doClose() throws Throwable {
		try{
			if(channel != null ) {
				channel.close();
			}
		} catch (Throwable e) {
			logger.warn(e.getMessage(), e);
		}
		try {
			Collection<com.alacoder.bee.remote.Channel> channels = getChannels();
			if(channels != null && channels.size() > 0) {
				for(com.alacoder.bee.remote.Channel ch : channels) {
					try {
						ch.close();
					} catch (Throwable e) {
						logger.warn(e.getMessage(), e);
					}
				}
			}
			
		} catch (Throwable e) {
			logger.warn(e.getMessage(), e);
		}
		 try {
	            if (bootstrap != null) { 
	                // release external resource.
	                bootstrap.releaseExternalResources();
	            }
	        } catch (Throwable e) {
	            logger.warn(e.getMessage(), e);
	        }
	        try {
	            if (channels != null) {
	                channels.clear();
	            }
	        } catch (Throwable e) {
	            logger.warn(e.getMessage(), e);
	        }
	}
	
	public Collection<Channel> getChannels() {
        Collection<Channel> chs = new HashSet<Channel>();
        for (Channel channel : this.channels.values()) {
            if (channel.isConnected()) {
                chs.add(channel);
            } else {
                channels.remove(NetUtils.toAddressString(channel.getRemoteAddress()));
            }
        }
        return chs;
    }

    public Channel getChannel(InetSocketAddress remoteAddress) {
        return channels.get(NetUtils.toAddressString(remoteAddress));
    }

    public boolean isBound() {
        return channel.isBound();
    }

}
