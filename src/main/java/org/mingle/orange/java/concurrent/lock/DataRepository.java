/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.lock;

/**
 * 大型数据集合一般使用读写锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class DataRepository {
	protected final ReadWriteLock rw = new RWLock();
	
	public void access() throws InterruptedException {
		rw.readLock().acquire();
		try {
			/* read data */
		} finally {
			rw.readLock().release();
		}
	}
	
	public void modify() throws InterruptedException {
		rw.writeLock().acquire();
		try {
			/* write data */
		} finally {
			rw.writeLock().release();
		}
	}
}
