/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.threadpermessage;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public interface Channel<T> {
	void put(T t) throws InterruptedException;
	T take() throws InterruptedException;
}
