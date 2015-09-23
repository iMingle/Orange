/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

/**
 * 只有一个后继的阶段
 * 
 * @since 1.8
 * @author Mingle
 */
public class SingleOutputPushStage {
	private PushStage next1 = null;

	protected synchronized PushStage next1() {
		return next1;
	}

	public synchronized void attach1(PushStage s) {
		next1 = s;
	}
}

/**
 * 有两个后继的阶段
 */
class DualOutputPushStage extends SingleOutputPushStage {
	private PushStage next2 = null;

	protected synchronized PushStage next2() {
		return next2;
	}

	public synchronized void attach2(PushStage s) {
		next2 = s;
	}
}