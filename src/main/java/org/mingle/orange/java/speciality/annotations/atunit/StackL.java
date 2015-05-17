/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations.atunit;

import java.util.*;

/**
 * 被测试类
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class StackL<T> {
	private LinkedList<T> list = new LinkedList<T>();

	public void push(T v) {
		list.addFirst(v);
	}

	public T top() {
		return list.getFirst();
	}

	public T pop() {
		return list.removeFirst();
	}
}
