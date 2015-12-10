/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程
 *
 * @since 1.8
 * @author Mingle
 */
public class SimpleDaemons implements Runnable {
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);	// 必须在start()之前调用
			daemon.start();
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}

}
