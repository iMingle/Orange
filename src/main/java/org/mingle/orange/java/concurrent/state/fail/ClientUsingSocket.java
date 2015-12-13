/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.fail;

import java.io.IOException;
import java.net.Socket;

/**
 * 失败重试
 * 
 * @since 1.8
 * @author Mingle
 */
public class ClientUsingSocket {
	int portnumber = 1234;
	String server = "gee";

	Socket retryUntilConnected() throws InterruptedException {
		// first delay is randomly chosen between 5 and 10secs
		long delayTime = 5000 + (long) (Math.random() * 5000);
		for (;;) {
			try {
				return new Socket(server, portnumber);
			} catch (IOException ex) {
				Thread.sleep(delayTime);
				delayTime = delayTime * 3 / 2 + 1; // increase 50%
			}
		}
	}
}
