/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.construct;

/**
 * 自管理的同步
 * 
 * @since 1.8
 * @author Mingle
 */
public class OwnedPartWithGuard {
	protected boolean cond = false;
	final Object lock;

	OwnedPartWithGuard(Object owner) {
		lock = owner;
	}

	void await() throws InterruptedException {
		synchronized (lock) {
			while (!cond)
				lock.wait();
			// ...
		}
	}

	void signal(boolean c) {
		synchronized (lock) {
			cond = c;
			lock.notifyAll();
		}
	}
}
