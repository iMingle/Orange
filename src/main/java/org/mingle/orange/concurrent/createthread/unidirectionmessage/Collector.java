/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

/**
 * 收集器
 * 
 * @since 1.8
 * @author Mingle
 */
public class Collector extends SingleOutputPushStage implements
		DualInputPushStage {
	public void putA(Box p) {
		next1().putA(p);
	}

	public void putB(Box p) {
		next1().putA(p);
	}
}
