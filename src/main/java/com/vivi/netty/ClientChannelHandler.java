package com.vivi.netty;

import io.netty.channel.ChannelHandlerContext;

public interface ClientChannelHandler {

	void channelRead0(ChannelHandlerContext ctx, String msg);

}
