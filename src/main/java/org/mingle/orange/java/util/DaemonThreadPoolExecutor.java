/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.mingle.orange.java.speciality.concurrency.DaemonThreadFactory;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, 
				new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
	}

}
