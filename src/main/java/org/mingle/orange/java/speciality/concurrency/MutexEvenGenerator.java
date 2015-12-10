/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 互斥Lock偶数产生器
 *
 * @since 1.8
 * @author Mingle
 */
public class MutexEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	private Lock lock = new ReentrantLock();
	
	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.concurrency.IntGenerator#next()
	 */
	@Override
	public synchronized int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
			return currentEvenValue;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}

}
