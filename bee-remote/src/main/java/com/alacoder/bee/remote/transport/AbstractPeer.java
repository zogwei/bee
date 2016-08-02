/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: AbstractPeer.java
 * @Package com.alacoder.bee.remote.transport
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:20:27
 * @version V1.0
 */

package com.alacoder.bee.remote.transport;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.common.URL;
import com.alacoder.bee.remote.Channel;
import com.alacoder.bee.remote.ChannelHandler;
import com.alacoder.bee.remote.Endpoint;
import com.alacoder.bee.remote.RemotingException;

/**
 * @ClassName: AbstractPeer
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月29日 下午3:20:27
 *
 */

public abstract class AbstractPeer implements Endpoint,ChannelHandler{

	private final ChannelHandler handler;
	
	private volatile URL url;
	
	private volatile boolean closed;
	
	public AbstractPeer(URL url, ChannelHandler handler) {
		if(url == null) {
			throw new IllegalArgumentException(" url == null");
		}
		if(handler == null) {
			throw new IllegalArgumentException(" handler == null");
		}
		this.url = url;
		this.handler = handler;
	}
	
	public void send(Object message) throws RemotingException {
		send(message,url.getParameter(Constants.SENT_KEY, false));
	}
	
	public void close(){
		closed = true;
	}
	
	public void close(int tiemout) {
		close();
	}
	
	public URL getUrl() {
		return this.url;
	}
	
	public void setUrl(URL url) {
		if(url == null) {
			throw new IllegalArgumentException("url is null");
		}
		this.url = url;
	}
	
	public ChannelHandler getChannelHandler() {
		if(handler instanceof ChannelHandlerDelegate){
			return ((ChannelHandlerDelegate)handler).getHandler();
		} else {
			return handler;
		}
	}
	
	public ChannelHandler getDelegateHandler(){
		return handler;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public void connected(Channel ch) throws RemotingException{
		if(closed) {
			return;
		}
		handler.connected(ch);
	}
	
	public void disconnected(Channel ch) throws RemotingException{
		handler.disconnected(ch);
	}
	
    public void sent(Channel ch, Object msg) throws RemotingException {
        if (closed) {
            return;
        }
        handler.sent(ch, msg);
    }

    public void received(Channel ch, Object msg) throws RemotingException {
        if (closed) {
            return;
        }
        handler.received(ch, msg);
    }

    public void caught(Channel ch, Throwable ex) throws RemotingException {
        handler.caught(ch, ex);
    }
	
}
