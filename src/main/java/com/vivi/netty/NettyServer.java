package com.vivi.netty;

/**
 * @author huangwenwei
 *
 */
public interface NettyServer {

	NettyServer bind(int port);
	
	void run() throws Exception;
}
