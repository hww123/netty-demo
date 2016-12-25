package com.vivi.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	// @Override
	// public void channelActive(final ChannelHandlerContext ctx) { // (1)
	// final ByteBuf time = ctx.alloc().buffer(4); // (2)
	//// time.writeInt((int) (System.currentTimeMillis() / 1000L +
	// 2208988800L));
	// String msg = "this is vivi calling";
	// time.writeBytes(msg.getBytes());
	// final ChannelFuture f = ctx.writeAndFlush(time); // (3)
	// f.addListener(new ChannelFutureListener() {
	// public void operationComplete(ChannelFuture future) {
	// assert f == future;
	// ctx.close();
	// }
	// });
	// }

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
		ctx.write(in);
	};

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
