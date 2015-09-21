/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.threadpermessage;

import org.mingle.orange.concurrent.util.Helper;

/**
 * 每消息一线程
 * 
 * @since 1.8
 * @author Mingle
 */
public class ThreadPerMessageHost {
	protected long localState;
	protected final Helper helper = new Helper();

	protected synchronized void updateState() {
		localState = 2; // ...;
	}

	public void req() {
		updateState();
		new Thread(new Runnable() {
			public void run() {
				helper.handle();
			}
		}).start();
	}
}
