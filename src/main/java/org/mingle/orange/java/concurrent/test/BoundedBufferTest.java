/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.test;

import junit.framework.TestCase;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class BoundedBufferTest extends TestCase {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;
    
    void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(CAPACITY);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }
    
    void testIsFullAfterPuts() throws InterruptedException {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(CAPACITY);
        for (int i = 0; i < 10; i++)
            bb.put(i);
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }
    
    void testTakeBlocksWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(CAPACITY);
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
    
    /**
     * 大对象
     */
    class Big {
        double[] data = new double[100000];
    }
    
    /**
     * 测试资源泄露
     * @throws InterruptedException 
     */
    void testLeak() throws InterruptedException {
        SemaphoreBoundedBuffer<Big> bb = new SemaphoreBoundedBuffer<>(CAPACITY);
        int heapSize1 = 800;    /* 生成堆的快照 */
        for (int i = 0; i < CAPACITY; i++)
            bb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        int heapSize2 = 900;    /* 生成堆的快照 */
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }
}
