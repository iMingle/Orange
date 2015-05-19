/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class Daemons {

	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.out.print("d.isDaemon() = " + d.isDaemon() + ", ");
		TimeUnit.SECONDS.sleep(1);
	}

}

class Daemon implements Runnable {
	private Thread[] t = new Thread[10];

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.print("DaemonSpawn " + i + " started, ");
		}
		for (int i = 0; i < t.length; i++) {
			System.out.print("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		while (true)
			Thread.yield();
	}
	
}

class DaemonSpawn implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true)
			Thread.yield();
	}
	
}