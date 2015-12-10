/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程不运行finally子句
 *
 * @since 1.8
 * @author Mingle
 */
public class DaemonsDontRunFinally {
	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}
}

class ADaemon implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}
	
}