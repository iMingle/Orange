/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 同步通道
 * 
 * @since 1.8
 * @author Mingle
 */
public class SynchronousChannel {
	protected Object item = null; // to hold while in transit
	protected final Semaphore putPermit;
	protected final Semaphore takePermit;
	protected final Semaphore taken;

	public SynchronousChannel() {
		putPermit = new Semaphore(1);
		takePermit = new Semaphore(0);
		taken = new Semaphore(0);
	}

	public void put(Object x) throws InterruptedException {
		putPermit.acquire();
		item = x;
		takePermit.release();

		// Must wait until signalled by taker
		InterruptedException caught = null;
		for (;;) {
			try {
				taken.acquire();
				break;
			} catch (InterruptedException ie) {
				caught = ie;
			}
		}

		if (caught != null)
			throw caught; // can now rethrow
	}

	public Object take() throws InterruptedException {
		takePermit.acquire();
		Object x = item;
		item = null;
		putPermit.release();
		taken.release();
		return x;
	}
}
