/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.cancelclose;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 支持关闭的生产者-消费者日志服务
 * 
 * @since 1.8
 * @author Mingle
 */
public class LogService {
	private static final int CAPACITY = 100;
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private final PrintWriter writer;
	
	private boolean isShutdown;
	private int reservations;

	public LogService(PrintWriter writer) {
		this.queue = new LinkedBlockingQueue<>(CAPACITY);
		this.writer = writer;
		this.loggerThread = new LoggerThread();
	}
	
	public void start() {
		/**
		 * 通过注册一个一个关闭钩子来停止日志服务
		 */
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LogService.this.stop();
			}
		});
		loggerThread.start();
	}
	
	public void stop() {
		synchronized (this) {
			isShutdown = true;
		}
		loggerThread.interrupt();
	}
	
	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if (isShutdown)
				throw new IllegalStateException("is shutdown");
			reservations++;
		}
		queue.put(msg);
	}

	private class LoggerThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					try {
						synchronized (LogService.this) {
							if (isShutdown && reservations == 0)
								break;
						}
						String msg = queue.take();
						synchronized (LogService.this) {
							reservations--;
						}
						writer.println(msg);
					} catch (InterruptedException e) {
						/* retry */
					}
				}
			} finally {
				writer.close();
			}
		}
	}
}

/**
 * 关闭ExecutorService
 */
class LogService2 {
	private final ExecutorService exec = Executors.newSingleThreadExecutor();
	private final PrintWriter writer;
	
	public LogService2(PrintWriter writer) {
		this.writer = writer;
	}

	public void stop() throws InterruptedException {
		try {
			exec.shutdown();
			exec.awaitTermination(1, TimeUnit.SECONDS);
		} finally {
			writer.close();
		}
	}
	
	public void log(String msg) throws InterruptedException {
		try {
			exec.execute(new WriteTask(msg));
		} catch (RejectedExecutionException ignored) {}
	}
	
	private class WriteTask implements Runnable {
		private String msg;

		public WriteTask(String msg) {
			this.msg = msg;
		}
		
		@Override
		public void run() {
			writer.println(msg);
		}
	}
}