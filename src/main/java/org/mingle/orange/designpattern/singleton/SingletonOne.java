/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.singleton;


public class SingletonOne {
	
	private static final SingletonOne INSTANCE = new SingletonOne();
	
	private SingletonOne() {
		// 防止借助AccessibleObject.setAccessible的反射机制调用私有构造器
		if (INSTANCE != null)
			throw new IllegalStateException("singleton, cannot create another object");
	}

	public static SingletonOne getInstance() {
		return INSTANCE;
	}
}
