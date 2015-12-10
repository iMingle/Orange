/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.construct;

/**
 * 不变类
 * 
 * @since 1.8
 * @author Mingle
 */
public class ImmutablePoint {
	private final int x;
	private final int y;

	public ImmutablePoint(int initX, int initY) {
		x = initX;
		y = initY;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}
}

class Dot {
	protected ImmutablePoint loc;
	
	public Dot(int x, int y) {
		loc = new ImmutablePoint(x, y);
	}
	
	public synchronized ImmutablePoint location() {
		return loc;
	}
	
	protected synchronized void updateLoc(ImmutablePoint newLoc) {
		loc = newLoc;
	}
	
	public void moveTo(int x, int y) {
		updateLoc(new ImmutablePoint(x, y));
	}
	
	/**
	 * loc可变,此方法必须同步
	 * 
	 * @param delta
	 */
	public synchronized void shiftX(int delta) {
		updateLoc(new ImmutablePoint(loc.x() + delta, loc.y()));
	}
}