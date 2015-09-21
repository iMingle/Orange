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
public class PlainWorkerPool implements Executor {
	protected final Channel<Runnable> workQueue;

	@Override
	public void execute(Runnable r) {
		try {
			workQueue.put(r);
		} catch (InterruptedException ie) { // postpone response
			Thread.currentThread().interrupt();
		}
	}

	public PlainWorkerPool(Channel<Runnable> ch, int nworkers) {
		workQueue = ch;
		for (int i = 0; i < nworkers; ++i)
			activate();
	}

	protected void activate() {
		Runnable runLoop = new Runnable() {
			public void run() {
				try {
					for (;;) {
						Runnable r = workQueue.take();
						r.run();
					}
				} catch (InterruptedException ie) {
				} // die
			}
		};
		new Thread(runLoop).start();
	}
}
