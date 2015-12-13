/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.construct;

import org.mingle.orange.java.concurrent.util.Helper;

/**
 * 开放调用(非同步发送消息)弱点,会锁定整个service()方法
 * 
 * @since 1.8
 * @author Mingle
 */
public class ServerWithAssignableHelper {
	private double state;
	private Helper helper = new Helper();

	synchronized void setHelper(Helper h) {
		helper = h;
	}

	public void service() {
		Helper h;
		synchronized (this) {
			state = 2.0f;
			h = helper;
		}
		h.operation();
	}

	/**
	 * 会锁定整个service()方法
	 */
	public synchronized void synchedService() { // see below
		service();
	}
	
	public synchronized double getState() {
		return state;
	}
}
