/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.constant;

/**
 * final不变性
 * 
 * @since 1.8
 * @author Mingle
 */
public class ImmutableAdder {
	private final int offset;

	public ImmutableAdder(int offset) {
		this.offset = offset;
	}

	public int addOffset(int b) {
		return offset + b;
	}
}
