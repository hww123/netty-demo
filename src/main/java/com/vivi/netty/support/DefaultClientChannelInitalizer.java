package com.vivi.netty.support;

import com.vivi.netty.ClientChannelHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class DefaultClientChannelInitalizer extends ChannelInitializer<SocketChannel> {

	private ClientChannelHandler channelHandler;

	public DefaultClientChannelInitalizer(ClientChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
		pipeline.addLast("handler", new NettyClientHandler(channelHandler));

	}

}
