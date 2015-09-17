/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.limit;

import org.mingle.orange.concurrent.util.Helper;

/**
 * 开放调用(非同步发送消息)
 * 
 * @since 1.8
 * @author Mingle
 */
public class ServerWithOpenCall {
	private double state;
	private final Helper helper = new Helper();

	public void service() {
		updateState();
		helper.operation();
	}

	public synchronized void updateState() {
		state = 2.0f; // set to some new value
	}

	public synchronized double getState() {
		return state;
	}
}
