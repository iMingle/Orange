/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程中断通过ReentrantLock的中断能力
 *
 * @since 1.8
 * @author Mingle
 */
class BlockedMutex {
	private Lock lock = new ReentrantLock();
	
	public BlockedMutex() {
		lock.lock();
	}
	
	public void f() {
		try {
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blcoked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
	
}

public class Interrupting2 {

	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Blcoked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}

}
