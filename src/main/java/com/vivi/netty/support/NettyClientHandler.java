package com.vivi.netty.support;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<String>  {
	
	private com.vivi.netty.ClientChannelHandler channelHandler;
	
	public NettyClientHandler(com.vivi.netty.ClientChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		channelHandler.channelRead0(ctx, msg);
	}
	
	
}
