/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 同步偶数产生器
 *
 * @since 1.8
 * @author Mingle
 */
public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	
	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.concurrency.IntGenerator#next()
	 */
	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}

}
