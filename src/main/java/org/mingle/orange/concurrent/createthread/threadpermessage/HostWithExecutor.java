/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.threadpermessage;

import org.mingle.orange.concurrent.util.Helper;

/**
 * 用执行器处理请求
 * 
 * @since 1.8
 * @author Mingle
 */
public class HostWithExecutor {
	protected long localState;
	protected final Helper helper = new Helper();
	protected final Executor executor;

	public HostWithExecutor(Executor e) {
		executor = e;
	}

	protected synchronized void updateState() {
		localState = 2; // ...;
	}

	public void req() {
		updateState();
		executor.execute(new Runnable() {
			public void run() {
				helper.handle();
			}
		});
	}
}
