/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state;

/**
 * 定时等待
 * 
 * @since 1.8
 * @author Mingle
 */
public class TimeOutBoundedCounter {
	static final long MIN = 0; // minimum allowed value

	static final long MAX = 10; // maximum allowed value

	protected long count = 0;

	protected long TIMEOUT = 5000; // for illustration

	synchronized void inc() throws InterruptedException {
		if (count >= MAX) {
			long start = System.currentTimeMillis();
			long waitTime = TIMEOUT;

			for (;;) {
				if (waitTime <= 0)
					throw new TimeoutException(TIMEOUT);
				else {
					try {
						wait(waitTime);
					} catch (InterruptedException ie) {
						throw ie; // coded this way just for emphasis
					}
					if (count < MAX)
						break;
					else {
						long now = System.currentTimeMillis();
						waitTime = TIMEOUT - (now - start);
					}
				}
			}
		}

		++count;
		notifyAll();
	}

	synchronized void dec() throws InterruptedException {
		if (count >= MIN) {
			long start = System.currentTimeMillis();
			long waitTime = TIMEOUT;
			
			for (;;) {
				if (waitTime < 0)
					throw new TimeoutException(TIMEOUT);
				else {
					try {
						wait(waitTime);
					} catch (InterruptedException ie) {
						throw ie;
					}
					
					if (count > MIN)
						break;
					else {
						long now = System.currentTimeMillis();
						waitTime = TIMEOUT - (now - start);
					}
				}
			}
		}
		
		count--;
		notifyAll();
	}
}
