/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.eventdriven;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 触发任务
 * 
 * @since 1.8
 * @author Mingle
 */
public class PollingWorker implements Runnable {
	private List<IOEventTask> tasks = new LinkedList<>();
	private long sleepTime = 100;

	void register(IOEventTask t) {
		tasks.add(t);
	}

	void deregister(IOEventTask t) {
		tasks.remove(t);
	}

	public void run() {
		try {
			for (;;) {
				for (Iterator<IOEventTask> it = tasks.iterator(); it.hasNext();) {
					IOEventTask t = it.next();
					if (t.done())
						deregister(t);
					else {
						boolean trigger;
						try {
							trigger = t.input().available() > 0;
						} catch (IOException ex) {
							trigger = true; // trigger if exception on check
						}
						if (trigger)
							t.run();
					}
				}
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException ie) {}
	}
}
