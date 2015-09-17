/**
 * Copyright (c) 2015, Mingle. All rights reserved.
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
