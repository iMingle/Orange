/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 锁的顺序化管理器
 * 
 * @since 1.8
 * @author Mingle
 */
public class LockManager {
	protected void sortLocks(Sync[] locks) {}
	
	public void runWithinLocks(Runnable op, Sync[] locks) throws InterruptedException {
		sortLocks(locks);
		
		// for help in recovering from exceptions
		int lastLocked = -1;
		InterruptedException caught = null;
		
		try {
			for (int i = 0; i < locks.length; i++) {
				locks[i].acquire();
				lastLocked = i;
			}
			
			op.run();
		} catch (InterruptedException e) {
			caught = e;
		} finally {
			for (int i = lastLocked; i >= 0; i--)
				locks[i].release();
			if (caught != null)
				throw caught;
		}
	}
}
