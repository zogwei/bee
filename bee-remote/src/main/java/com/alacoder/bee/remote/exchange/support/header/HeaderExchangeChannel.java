/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: HeaderExchangeChannel.java
 * @Package com.alacoder.bee.remote.exchange.support.header
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月3日 上午10:54:58
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange.support.header;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.RemotingException;
import com.alacoder.bee.remote.exchange.ExchangeChannel;
import com.alacoder.bee.remote.exchange.Request;
import com.alacoder.bee.remote.exchange.Response;
import com.alacoder.bee.remote.exchange.ResponseFuture;
import com.alacoder.bee.remote.exchange.support.DefaultFuture;

/**
 * @ClassName: HeaderExchangeChannel
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年8月3日 上午10:54:58
 *
 */

public class HeaderExchangeChannel implements ExchangeChannel{

	private static final Logger logger = LoggerFactory.getLogger(HeaderExchangeChannel.class);
	
	private static final String CHANNEL_KEY = HeaderExchangeChannel.class.getName() + ".CHANNEL";
	
	private final Channel channel;
	
	private volatile boolean closed = false;
	
	HeaderExchangeChannel(Channel channel) {
		if (channel == null) {
			throw new IllegalArgumentException("channel == null");
		}
		this.channel = channel;
	}
	
	static HeaderExchangeChannel getOrAddChannel(Channel ch) {
		if( ch == null ) {
			return null;
		}
		HeaderExchangeChannel ret = (HeaderExchangeChannel)ch.getAttribute(CHANNEL_KEY);
		if( ret == null ) {
			ret = new HeaderExchangeChannel(ch);
			if(ch.isConnected()){
				ch.setAttribute(CHANNEL_KEY, ret);
			}
		}
		
		return ret;
	}
	
	static void removeChannelIfDisconnected(Channel ch) {
		if ( ch != null && ! ch.isConnected() ) {
			ch.removeAttribute(CHANNEL_KEY);
		}
	}
	
	public void send(Object message) throws RemotingException {
		 send(message, getUrl().getParameter(Constants.SENT_KEY, false));
	}
	
	public void send(Object message, boolean sent) throws RemotingException {
		 if (closed) {
	            throw new RemotingException(this.getLocalAddress(), null, "Failed to send message " + message + ", cause: The channel " + this + " is closed!");
	     }
		 if(message instanceof Request
				 || message instanceof Response
				 || message instanceof String) {
			 channel.send(message, sent);
		 } else {
			 Request request = new Request();
			 request.setVersion("2.0.0");
			 request.setTwoWay(false);
			 request.setData(message);
			 channel.send(message, sent);
		 }
	}
	
	public ResponseFuture request(Object request) throws RemotingException {
		return request(request, channel.getUrl().getPositiveParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT));
	}
	
	public ResponseFuture request(Object request, int timeout) throws RemotingException {
		if (closed) {
            throw new RemotingException(this.getLocalAddress(), null, "Failed to send request " + request + ", cause: The channel " + this + " is closed!");
        }
		Request req = new Request();
		req.setVersion("2.0.0");
		req.setTwoWay(true);
		req.setData(request);
		DefaultFuture future = new DefaultFuture(channel, req, timeout);
		try{
			channel.send(req);
		} catch ( RemotingException e) {
			future.cancel();
			throw e;
		}
		return future;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public void close() {
		try {
			channel.close();
		} catch (Throwable e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	public void close(int timeout) {
		if (closed) {
			return;
		}
		closed = true;
		if (timeout > 0 ) {
			long start = System.currentTimeMillis();
			while (DefaultFuture.hasFuture(HeaderExchangeChannel.this) 
                    && System.currentTimeMillis() - start < timeout) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    logger.warn(e.getMessage(), e);
                }
            }
		}
	}
	
    public InetSocketAddress getLocalAddress() {
        return channel.getLocalAddress();
    }

    public InetSocketAddress getRemoteAddress() {
        return channel.getRemoteAddress();
    }

    public URL getUrl() {
        return channel.getUrl();
    }

    public boolean isConnected() {
        return channel.isConnected();
    }

    public ChannelHandler getChannelHandler() {
        return channel.getChannelHandler();
    }

    public ExchangeHandler getExchangeHandler() {
        return (ExchangeHandler) channel.getChannelHandler();
    }
    
    public Object getAttribute(String key) {
        return channel.getAttribute(key);
    }

    public void setAttribute(String key, Object value) {
        channel.setAttribute(key, value);
    }

    public void removeAttribute(String key) {
        channel.removeAttribute(key);
    }

    public boolean hasAttribute(String key) {
        return channel.hasAttribute(key);
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
        HeaderExchangeChannel other = (HeaderExchangeChannel) obj;
        if (channel == null) {
            if (other.channel != null) return false;
        } else if (!channel.equals(other.channel)) return false;
        return true;
    }

    @Override
    public String toString() {
        return channel.toString();
    }
}
