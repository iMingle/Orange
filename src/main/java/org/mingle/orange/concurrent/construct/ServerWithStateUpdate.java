/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.construct;

import org.mingle.orange.concurrent.util.Helper;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class ServerWithStateUpdate {
	private double state;
	private final Helper helper = new Helper();

	public synchronized void service() {
		state = 2.0f;	// set to some new value
		helper.operation();	// 花费时间很长的话,那么此方法需要阻塞无法接受的时间
	}

	public synchronized double getState() {
		return state;
	}
}
