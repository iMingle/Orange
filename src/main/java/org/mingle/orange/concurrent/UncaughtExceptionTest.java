/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class UncaughtExceptionTest {
	private static int count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (count >= 0) {
					System.out.println(count++);
					
					if (100000 == count) {
						count = count / 0;
					}
				}
			}
		});
		
		Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("uncaughtException handler");
			}
		};
		
		/**
		 * method one
		 */
		thread.setUncaughtExceptionHandler(handler);
		/**
		 * method two, if thread has default handler, invoke default handler.
		 */
		ThreadGroup tg = new ThreadGroup("mingle");
		tg.uncaughtException(thread, new Throwable());
		thread.getThreadGroup().uncaughtException(thread, new Throwable());
		thread.start();
	}

}
