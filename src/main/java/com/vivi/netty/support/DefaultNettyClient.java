package com.vivi.netty.support;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.vivi.netty.ClientChannelHandler;
import com.vivi.netty.NettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DefaultNettyClient implements NettyClient {

	private String addr;
	private int port;
	private ClientChannelHandler channelHandler;
	
	public DefaultNettyClient(ClientChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}

	public NettyClient bind(String addr, int port) {
		this.addr = addr;
		this.port = port;
		return this;
	}

	public void run() throws InterruptedException, IOException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap()
				.group(group)
				.channel(NioSocketChannel.class)
				.handler(new DefaultClientChannelInitalizer(channelHandler));
			
			Channel channel = bootstrap.connect(addr, port).sync().channel();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			if (channel != null) {
				while (true) {
					channel.writeAndFlush(br.readLine() + "\n");
				}
			}
		
		}
		finally {
			group.shutdownGracefully();
		}
	}

}
