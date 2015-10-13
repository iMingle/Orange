/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.cancelclose;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 处理不可中断的阻塞
 * 
 * @since 1.8
 * @author Mingle
 */
public class ReaderThread extends Thread {
	private final Socket socket;
	private final InputStream in;
	
	public ReaderThread(Socket socket) throws IOException {
		this.socket = socket;
		this.in = socket.getInputStream();
	}
	
	@Override
	public void interrupt() {
		try {
			socket.close();	// 由于read和write方法不响应中断,调用close方法可以使阻塞的线程抛出异常
		} catch (IOException ignored) {
			
		} finally {
			super.interrupt();
		}
	}
	
	@Override
	public void run() {
		try {
			byte[] buf = new byte[1024];
			while (true) {
				int count = in.read(buf);
				if (count < 0)
					break;
				else if (count > 0)
					processBuffer(buf, count);
			}
		} catch (IOException e) {
			/* 允许线程退出 */
		}
	}

	private void processBuffer(byte[] buf, int count) {
		
	}
}
