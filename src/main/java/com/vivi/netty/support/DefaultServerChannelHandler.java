package com.vivi.netty.support;

import com.vivi.netty.ServerChannelHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

/**
 * @author huangwenwei
 *
 */
public class DefaultServerChannelHandler implements ServerChannelHandler {

	public void handlerAdded(ChannelHandlerContext ctx, ChannelGroup channels) {
		Channel incoming = ctx.channel();
		channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
		channels.add(ctx.channel());
	}

	public void handlerRemoved(ChannelHandlerContext ctx, ChannelGroup channels) {
		Channel incoming = ctx.channel();
		channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
	}

	public void channelRead0(ChannelHandlerContext ctx, String s, ChannelGroup channels) {
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			if (channel != incoming) {
				channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s + "\n");
			} else {
				channel.writeAndFlush("[you]" + s + "\n");
			}
		}
		
	}

	public void channelActive(ChannelHandlerContext ctx) {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
	}

	public void channelInactive(ChannelHandlerContext ctx) {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Channel incoming = ctx.channel();
		System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
		cause.printStackTrace();
		ctx.close();
	}
	
}
