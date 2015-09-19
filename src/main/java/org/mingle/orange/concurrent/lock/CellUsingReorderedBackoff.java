/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

/**
 * 重新排序
 * 
 * @since 1.8
 * @author Mingle
 */
public class CellUsingReorderedBackoff {
	private long value;
	private final Mutex mutex = new Mutex();

	private static boolean trySwap(CellUsingReorderedBackoff a,
			CellUsingReorderedBackoff b) throws InterruptedException {
		boolean success = false;

		if (a.mutex.attempt(0)) {
			try {
				if (b.mutex.attempt(0)) {
					try {
						long t = a.value;
						a.value = b.value;
						b.value = t;
						success = true;
					} finally {
						b.mutex.release();
					}
				}
			} finally {
				a.mutex.release();
			}
		}

		return success;

	}

	void swapValue(CellUsingReorderedBackoff other) {
		if (this == other)	// alias check required
			return;
		try {
			// 失败时以相反的顺序锁定
			while (!trySwap(this, other) && !trySwap(other, this))
				Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
