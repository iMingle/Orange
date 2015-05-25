/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显式的Lock对象创建临界区
 *
 * @since 1.8
 * @author Mingle
 */
class ExplicitPairManager1 extends PairManager {
	private Lock lock = new ReentrantLock();

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.concurrency.PairManager#increment()
	 */
	@Override
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}

}

class ExplicitPairManager2 extends PairManager {
	private Lock lock = new ReentrantLock();

	/* (non-Javadoc)
	 * @see org.mingle.orange.java.speciality.concurrency.PairManager#increment()
	 */
	@Override
	public void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
	
}

public class ExplicitCriticalSection {
	public static void main(String[] args) {
		PairManager pman1 = new ExplicitPairManager1(),
				pman2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}
}