package com.vivi.netty.test.chat;

import com.vivi.netty.support.DefaultNettyServer;
import com.vivi.netty.support.DefaultServerChannelHandler;

public class Demo2 {
	
	public static void main(String[] args) throws Exception {
		new DefaultNettyServer(new DefaultServerChannelHandler()).bind(9999).run();
	}

}
