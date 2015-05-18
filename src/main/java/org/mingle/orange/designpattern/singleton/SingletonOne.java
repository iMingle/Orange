/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.singleton;

public class SingletonOne {
	
	private static final SingletonOne singleton = new SingletonOne();
	
	private SingletonOne() {
		
	}

	public static SingletonOne getInstance() {
		return singleton;
	}
}
