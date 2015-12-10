/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.condvar;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * 一把锁的多个条件变量
 * 
 * @since 1.8
 * @author Mingle
 */
public class PThreadsStyleBuffer {
	private final Mutex mutex = new Mutex();
	private final CondVar notFull = new CondVar(mutex);
	private final CondVar notEmpty = new CondVar(mutex);
	private int count = 0;
	private int takePtr = 0;
	private int putPtr = 0;
	private final Object[] array;

	public PThreadsStyleBuffer(int capacity) {
		array = new Object[capacity];
	}

	public void put(Object x) throws InterruptedException {
		mutex.acquire();
		try {
			while (count == array.length)
				notFull.await();

			array[putPtr] = x;
			putPtr = (putPtr + 1) % array.length;
			++count;
			notEmpty.signal();
		} finally {
			mutex.release();
		}
	}

	public Object take() throws InterruptedException {
		Object x = null;
		mutex.acquire();
		try {
			while (count == 0)
				notEmpty.await();

			x = array[takePtr];
			array[takePtr] = null;
			takePtr = (takePtr + 1) % array.length;
			--count;
			notFull.signal();
		} finally {
			mutex.release();
		}
		return x;
	}
}

class CondVar {
	protected final Sync mutex;

	public CondVar(Sync lock) {
		mutex = lock;
	}
	
	public void await() throws InterruptedException {}
	
	public void timewait(long ms)  throws InterruptedException {}
	
	public void signal() {}		// analog of notify
	
	public void broadcast() {}	// analog of notifyAll
}