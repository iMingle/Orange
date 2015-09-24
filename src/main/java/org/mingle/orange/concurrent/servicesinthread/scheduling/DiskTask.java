/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.servicesinthread.scheduling;

import java.util.concurrent.Semaphore;

import org.mingle.orange.concurrent.state.transaction.Failure;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public abstract class DiskTask implements Runnable {
	protected final int cylinder; // read/write parameters
	protected final byte[] buffer;
	protected Failure exception = null; // to relay out
	protected DiskTask next = null; // for use in queue
	protected final Semaphore done = new Semaphore(1); // status indicator

	DiskTask(int c, byte[] b) {
		cylinder = c;
		buffer = b;
	}

	abstract void access() throws Failure; // read or write

	public void run() {
		try {
			access();
		} catch (Failure ex) {
			setException(ex);
		} finally {
			done.release();
		}
	}

	void awaitCompletion() throws InterruptedException {
		done.acquire();
	}

	synchronized Failure getException() {
		return exception;
	}

	synchronized void setException(Failure f) {
		exception = f;
	}
}
