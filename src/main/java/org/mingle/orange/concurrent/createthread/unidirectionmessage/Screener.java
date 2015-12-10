/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.createthread.unidirectionmessage;

/**
 * 筛选阶段
 * 
 * @since 1.8
 * @author Mingle
 */
public class Screener extends DualOutputPushStage implements PushStage {

	protected final BoxPredicate predicate;

	public Screener(BoxPredicate p) {
		predicate = p;
	}

	@Override
	public void putA(final Box p) {
		if (predicate.test(p)) {
			new Thread(new Runnable() {
				public void run() {
					next1().putA(p);
				}
			}).start();
		} else
			next2().putA(p);
	}
}

interface BoxPredicate {
	boolean test(Box p);
}

class MaxSizePredicate implements BoxPredicate {

	protected final int max; // max size to let through

	public MaxSizePredicate(int maximum) {
		max = maximum;
	}

	public boolean test(Box p) {
		return p.size().height <= max && p.size().width <= max;
	}
}
