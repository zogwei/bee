/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote-netty
 * @Title: NettyHandler.java
 * @Package com.alacoder.bee.remote.netty
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 上午11:58:01
 * @version V1.0
 */

package com.alacoder.bee.remote.netty;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.utils.NetUtils;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;

/**
 * @ClassName: NettyHandler
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年8月1日 上午11:58:01
 *
 */
@Sharable
public class NettyHandler extends SimpleChannelHandler {

	private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>();
	
	private final URL url;
	
	private final ChannelHandler handler;
	
	public NettyHandler(URL url, ChannelHandler handler) {
		if(url == null) {
			throw new IllegalArgumentException("url is null");
		}
		if(handler == null) {
			throw new IllegalArgumentException("handler is null");
		}
		this.url = url ;
		this.handler = handler;
	}
	
	public Map<String, Channel> getChannels() {
		return channels;
	}
	
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		NettyChannel channel = NettyChannel.getOrAddChannel(ctx.getChannel(), url, handler);
		try {
			if (channel != null) {
				channels.put(NetUtils.toAddressString((InetSocketAddress) ctx.getChannel().getRemoteAddress()), channel);
			}
			handler.connected(channel);
		} finally {
			NettyChannel.removeChannelIfDisconnected(ctx.getChannel());
		}
	}
	
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.getChannel(), url, handler);
        try {
            channels.remove(NetUtils.toAddressString((InetSocketAddress) ctx.getChannel().getRemoteAddress()));
            handler.disconnected(channel);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.getChannel());
        }
    }
    
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.getChannel(), url, handler);
        try {
            handler.received(channel, e.getMessage());
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.getChannel());
        }
    }
    
    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.writeRequested(ctx, e);
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.getChannel(), url, handler);
        try {
            handler.sent(channel, e.getMessage());
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.getChannel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.getChannel(), url, handler);
        try {
            handler.caught(channel, e.getCause());
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.getChannel());
        }
    }

    
    
}
