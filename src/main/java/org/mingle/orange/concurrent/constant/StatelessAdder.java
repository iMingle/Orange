/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.constant;

/**
 * 具有不变性的最简单对象
 * 
 * @since 1.8
 * @author Mingle
 */
public class StatelessAdder {
	public int add(int a, int b) {
		return a + b;
	}
}