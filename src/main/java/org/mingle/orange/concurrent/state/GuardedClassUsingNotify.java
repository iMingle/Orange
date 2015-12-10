/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state;

/**
 * notify方法模拟notifyAll方法
 * 
 * @since 1.8
 * @author Mingle
 */
public class GuardedClassUsingNotify {
	protected boolean cond = false;
	protected int nWaiting = 0; // count waiting threads

	protected synchronized void awaitCond() throws InterruptedException {
		while (!cond) {
			++nWaiting; // record fact that a thread is waiting
			try {
				wait();
			} catch (InterruptedException ie) {
				notify();
				throw ie;
			} finally {
				--nWaiting; // no longer waiting
			}
		}
	}

	protected synchronized void signalCond() {
		if (cond) { // simulate notifyAll
			for (int i = nWaiting; i > 0; --i) {
				notify();
			}
		}
	}
}
