/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 筷子,哲学家就餐问题
 *
 * @since 1.8
 * @author Mingle
 */
public class Chopstick {
	private boolean taken = false;
	
	public synchronized void take() throws InterruptedException {
		while (taken)
			wait();
		taken = true;
	}
	
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
