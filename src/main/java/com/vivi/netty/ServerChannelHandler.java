package com.vivi.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

public interface ServerChannelHandler  {

	void handlerAdded(ChannelHandlerContext ctx, ChannelGroup channels);

	void handlerRemoved(ChannelHandlerContext ctx, ChannelGroup channels);

	void channelRead0(ChannelHandlerContext ctx, String s, ChannelGroup channels);

	void channelActive(ChannelHandlerContext ctx);

	void channelInactive(ChannelHandlerContext ctx);

	void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);

}
