/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock来实现信号量
 * 
 * @since 1.8
 * @author Mingle
 */
public class SemaphoreOnLock {
	private final Lock lock = new ReentrantLock();
	// CONDITION PREDICATE: permitsAvailable (permits > 0)
	private final Condition permitsAvailable = lock.newCondition();
	private int permits;

	SemaphoreOnLock(int initialPermits) {
		lock.lock();
		try {
			permits = initialPermits;
		} finally {
			lock.unlock();
		}
	}

	// BLOCKS-UNTIL: permitsAvailable
	public void acquire() throws InterruptedException {
		lock.lock();
		try {
			while (permits <= 0)
				permitsAvailable.await();
			--permits;
		} finally {
			lock.unlock();
		}
	}

	public void release() {
		lock.lock();
		try {
			++permits;
			permitsAvailable.signal();
		} finally {
			lock.unlock();
		}
	}
}
