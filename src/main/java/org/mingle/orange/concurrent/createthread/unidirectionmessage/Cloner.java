/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

/**
 * 复制阶段
 * 
 * @since 1.8
 * @author Mingle
 */
public class Cloner extends DualOutputPushStage implements PushStage {

	@Override
	public void putA(Box p) {
		final Box p2 = p.duplicate();
		next1().putA(p);
		new Thread(new Runnable() {
			public void run() {
				next2().putA(p2);
			}
		}).start();
	}

}
