package com.vivi.netty.test.chat;

public class Client2 {

	public static void main(String[] args) throws Exception {
		new SimpleChatClient("localhost", 8080).run();
	}

}
