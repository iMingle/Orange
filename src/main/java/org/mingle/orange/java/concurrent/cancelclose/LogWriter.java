/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.cancelclose;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 不支持关闭的生产者-消费者日志服务
 * 
 * @since 1.8
 * @author Mingle
 */
public class LogWriter {
	private static final int CAPACITY = 100;
	private final BlockingQueue<String> queue;
	private final LoggerThread logger;

	public LogWriter(PrintWriter writer) {
		this.queue = new LinkedBlockingQueue<>(CAPACITY);
		this.logger = new LoggerThread(writer);
	}
	
	public void start() {
		logger.start();
	}
	
	public void log(String msg) throws InterruptedException {
		queue.put(msg);
	}

	private class LoggerThread extends Thread {
		private final PrintWriter writer;

		public LoggerThread(PrintWriter writer) {
			this.writer = writer;
		}
		
		@Override
		public void run() {
			try {
				while (true)
					writer.println(queue.take());
			} catch (InterruptedException ignored) {
				
			} finally {
				writer.close();
			}
		}
	}
}
