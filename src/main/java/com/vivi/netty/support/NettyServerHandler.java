package com.vivi.netty.support;

import com.vivi.netty.ServerChannelHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

	private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	private ServerChannelHandler channelHandler;

	public NettyServerHandler(ServerChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		channelHandler.handlerAdded(ctx, channels);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		channelHandler.handlerRemoved(ctx, channels);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception { // (4)
		channelHandler.channelRead0(ctx, s, channels);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		channelHandler.channelActive(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		channelHandler.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
		channelHandler.exceptionCaught(ctx, cause);
	}

	

}
