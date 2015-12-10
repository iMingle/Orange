/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.construct;

import org.mingle.orange.concurrent.lock.ReadWriteLock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class RWLock extends ReadWrite implements ReadWriteLock {
	class RLock implements Sync {
		public void acquire() throws InterruptedException {
			beforeRead();
		}

		public void release() {
			afterRead();
		}

		public boolean attempt(long msecs) throws InterruptedException {
			return beforeRead(msecs);
		}
	}

	class WLock implements Sync {
		public void acquire() throws InterruptedException {
			beforeWrite();
		}

		public void release() {
			afterWrite();
		}

		public boolean attempt(long msecs) throws InterruptedException {
			return beforeWrite(msecs);
		}
	}

	protected final RLock rlock = new RLock();
	protected final WLock wlock = new WLock();

	@Override
	public Sync readLock() {
		return rlock;
	}

	@Override
	public Sync writeLock() {
		return wlock;
	}

	public boolean beforeRead(long msecs) throws InterruptedException {
		return true;
		// ... time-out version of beforeRead ...
	}

	public boolean beforeWrite(long msecs) throws InterruptedException {
		return true;
		// ... time-out version of beforeWrite ...
	}

	@Override
	protected void doRead() {
	}

	@Override
	protected void doWrite() {
	}
}
