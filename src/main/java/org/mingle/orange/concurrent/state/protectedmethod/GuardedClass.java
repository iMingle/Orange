/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.protectedmethod;

/**
 * 受保护的等待
 * 
 * @since 1.8
 * @author Mingle
 */
public class GuardedClass {
	protected boolean cond = false;

	// PRE: lock held
	protected void awaitCond() throws InterruptedException {
		while (!cond)
			wait();
	}

	public synchronized void guardedAction() {
		try {
			awaitCond();
		} catch (InterruptedException ie) {
			// fail
		}
		// actions
	}
}
