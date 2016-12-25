package com.vivi.netty;

import java.io.IOException;

/**
 * @author huangwenwei
 *
 */
public interface NettyClient {
	
	NettyClient bind(String addr, int port);
	
	void run() throws InterruptedException, IOException;

}
