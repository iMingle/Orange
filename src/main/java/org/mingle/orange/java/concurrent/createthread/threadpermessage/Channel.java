/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.createthread.threadpermessage;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Channel<T> {
    void put(T t) throws InterruptedException;
    T take() throws InterruptedException;
}
