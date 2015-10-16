/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.test;

import junit.framework.TestCase;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class BoundedBufferTest extends TestCase {
	private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;
    
	void testIsEmptyWhenConstructed() {
		SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	void testIsFullAfterPuts() throws InterruptedException {
		SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
		for (int i = 0; i < 10; i++)
			bb.put(i);
		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
	
	void testTakeBlocksWhenEmpty() {
		final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
		Thread taker = new Thread() {
            public void run() {
                try {
                    @SuppressWarnings("unused")
					int unused = bb.take();
                    fail(); // if we get here, it's an error
                } catch (InterruptedException success) {
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
	}
}
