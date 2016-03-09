/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.util;

/**
 * 获得-释放协议
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Sync {
    void acquire() throws InterruptedException;
    void release();
    boolean attempt(long msec) throws InterruptedException;
}
