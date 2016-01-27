/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.sync;

import java.util.Random;

/**
 * 懒加载单例模式
 * 
 * @since 1.8
 * @author Mingle
 */
public class LazySingletonCounter {
    private final long initial;
    private long count;
    private static LazySingletonCounter s = null;
    private static final Object classLock = LazySingletonCounter.class;

    private LazySingletonCounter() {
        initial = Math.abs(new Random().nextLong() / 2);
        count = initial;
    }

    public static LazySingletonCounter instance() {
        synchronized (classLock) {
            if (s == null)
                s = new LazySingletonCounter();
            return s;
        }
    }

    public long next() {
        synchronized (classLock) {
            return count++;
        }
    }

    public void reset() {
        synchronized (classLock) {
            count = initial;
        }
    }
}
