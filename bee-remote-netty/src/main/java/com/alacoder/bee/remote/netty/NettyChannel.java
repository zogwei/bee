/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote-netty
 * @Title: NettyChannel.java
 * @Package com.alacoder.bee.remote.netty
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月1日 下午1:35:57
 * @version V1.0
 */

package com.alacoder.bee.remote.netty;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.ChannelFuture;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.RemotingException;
import com.alacoder.bee.remote.transport.AbstractChannel;

/**
 * @ClassName: NettyChannel
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年8月1日 下午1:35:57
 *
 */

public final class NettyChannel extends AbstractChannel {

	private static final Logger logger = LoggerFactory.getLogger(NettyChannel.class);
	
	private static final ConcurrentHashMap<org.jboss.netty.channel.Channel, NettyChannel> channelMap = new ConcurrentHashMap<org.jboss.netty.channel.Channel, NettyChannel>();

	private final org.jboss.netty.channel.Channel channel;
	
	private final Map<String, Object> attributes = new ConcurrentHashMap<String,Object>();
	
	private NettyChannel(org.jboss.netty.channel.Channel channel, URL url, ChannelHandler handler) {
		super(url, handler);
		if(channel == null) {
			throw new IllegalArgumentException(" netty channel == null");
		}
		this.channel = channel;
	}
	
	static NettyChannel getOrAddChannel(org.jboss.netty.channel.Channel ch, URL url, ChannelHandler handler) {
		if(ch == null) {
			return null;
		}
		NettyChannel ret = channelMap.get(ch);
		if(ret == null) {
			NettyChannel nc = new NettyChannel(ch,url,handler);
			if(ch.isConnected()){
				ret = channelMap.putIfAbsent(ch, nc);
			}
			if(ret == null) {
				ret = nc;
			}
		}
		return ret;
	}
	
	static void removeChannelIfDisconnected(org.jboss.netty.channel.Channel ch) {
		if(ch != null && ! ch.isConnected()) {
			channelMap.remove(ch);
		}
	}
	
	public InetSocketAddress getLocalAddress() {
		return (InetSocketAddress)channel.getLocalAddress();
	}
	
	public InetSocketAddress getRemoteAddress() {
		return (InetSocketAddress)channel.getRemoteAddress();
	}
	
	public boolean isConnected() {
		return channel.isConnected();
	}
	
	public void send(Object message,Boolean sent) throws RemotingException {
		super.send(message,sent);
		
		boolean success = true;
		int timeout = 0;
		try{
			ChannelFuture future = channel.write(message);
			if(sent) {
				timeout = getUrl().getPositiveParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
				success = future.await(timeout);
			}
			Throwable cause  = future.getCause();
			if( cause != null ) {
				throw cause;
			}
		} catch (Throwable e) {
			throw new RemotingException(this, "Failed to send message " + message + " to " + getRemoteAddress() + ", cause: " + e.getMessage(), e);
		}
		
		if(! success) {
            throw new RemotingException(this, "Failed to send message " + message + " to " + getRemoteAddress()
                    + "in timeout(" + timeout + "ms) limit");
        }
	}
	
	public void close() {
        try {
            super.close();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            removeChannelIfDisconnected(channel);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            attributes.clear();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Close netty channel " + channel);
            }
            channel.close();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }		
	}
	
    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }
    
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void setAttribute(String key, Object value) {
        if (value == null) { // The null value unallowed in the ConcurrentHashMap.
            attributes.remove(key);
        } else {
            attributes.put(key, value);
        }
    }

    public void removeAttribute(String key) {
        attributes.remove(key);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((channel == null) ? 0 : channel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        NettyChannel other = (NettyChannel) obj;
        if (channel == null) {
            if (other.channel != null) return false;
        } else if (!channel.equals(other.channel)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "NettyChannel [channel=" + channel + "]";
    }
    
}
