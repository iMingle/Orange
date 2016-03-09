/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

/**
 * 回退
 * 
 * @since 1.0
 * @author Mingle
 */
public class CellUsingBackoff {
    private long value;
    private final Mutex mutex = new Mutex();

    void swapValue(CellUsingBackoff other) {
        if (this == other)    // alias check required
            return;
        for (;;) {
            try {
                mutex.acquire();

                try {
                    if (other.mutex.attempt(0)) {
                        try {
                            long t = value;
                            value = other.value;
                            other.value = t;
                            return;
                        } finally {
                            other.mutex.release();
                        }
                    }
                } finally {
                    mutex.release();
                }

                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
