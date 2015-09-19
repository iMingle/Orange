/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.construct;

/**
 * 等待版本栈
 * 
 * @since 1.8
 * @author Mingle
 */
public class WaitingStack extends Stack {
	public synchronized void push(Object x) {
		super.push(x);
		notifyAll();
	}

	public synchronized Object waitingPop() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}

		try {
			return super.pop();
		} catch (StackEmptyException cannothappen) {
			// only possible if pop contains a programming error
			throw new Error("Internal implementation error");
		}
	}
}
