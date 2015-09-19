/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.construct;

/**
 * 原子性提交
 * 
 * @since 1.8
 * @author Mingle
 */
public class Optimistic {
	private State state; // reference to representation object

	@SuppressWarnings("unused")
	private synchronized State getState() {
		return state;
	}

	@SuppressWarnings("unused")
	private synchronized boolean commit(State assumed, State next) {
		if (state == assumed) {
			state = next;
			return true;
		} else
			return false;
	}
}

class State {
}

class OptimisticDot {
	protected ImmutablePoint loc;

	public OptimisticDot(int x, int y) {
		loc = new ImmutablePoint(x, y);
	}

	public synchronized ImmutablePoint location() {
		return loc;
	}

	protected synchronized boolean commit(ImmutablePoint assumed,
			ImmutablePoint next) {
		if (loc == assumed) {
			loc = next;
			return true;
		} else
			return false;
	}

	public synchronized void moveTo(int x, int y) {
		// bypass commit since unconditional
		loc = new ImmutablePoint(x, y);
	}

	public void shiftX(int delta) {
		boolean success = false;
		do {
			ImmutablePoint old = location();
			ImmutablePoint next = new ImmutablePoint(old.x() + delta, old.y());
			success = commit(old, next);
		} while (!success);
	}

}