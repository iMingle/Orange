/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.constant;

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
