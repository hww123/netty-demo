package com.vivi.netty.test.chat;

import java.io.IOException;

import com.vivi.netty.support.DefaultClientChannelHandler;
import com.vivi.netty.support.DefaultNettyClient;

public class Demo3 {

	public static void main(String[] args) throws InterruptedException, IOException {
		new DefaultNettyClient(new DefaultClientChannelHandler()).bind("192.168.1.2", 9999).run();
	}
}
