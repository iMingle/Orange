/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public interface ReadWriteLock {
	Sync readLock();
	Sync writeLock();
}
