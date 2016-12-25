package com.vivi.netty.support;

import com.vivi.netty.NettyServer;
import com.vivi.netty.ServerChannelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DefaultNettyServer implements NettyServer {
	
	private ServerChannelHandler channelHandler;

	private int port;
	
	public DefaultNettyServer(ServerChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}

	public NettyServer bind(int port) {
		this.port = port;
		return this;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workderGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap()
				.group(bossGroup, workderGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new DefautlServerChannelInitializer(channelHandler))
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
			
		}
		finally {
			workderGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	
}
