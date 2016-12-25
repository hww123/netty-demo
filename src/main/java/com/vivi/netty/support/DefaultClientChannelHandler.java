package com.vivi.netty.support;

import com.vivi.netty.ClientChannelHandler;

import io.netty.channel.ChannelHandlerContext;

public class DefaultClientChannelHandler implements ClientChannelHandler {

	public void channelRead0(ChannelHandlerContext ctx, String msg) {
		System.out.println(msg);
	}

}
