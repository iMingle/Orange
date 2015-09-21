/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.opencall;

import org.mingle.orange.concurrent.util.Helper;

/**
 * 开放调用
 * 
 * @since 1.8
 * @author Mingle
 */
public class OpenCallHost {
	protected long localState;
	protected final Helper helper = new Helper();

	protected synchronized void updateState() {
		localState = 2; // ...;
	}

	public void req() {
		updateState();
		helper.handle();
	}
}
