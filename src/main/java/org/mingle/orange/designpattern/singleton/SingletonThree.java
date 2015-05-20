/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.singleton;

/**
 * 静态内部类实现的单例模式
 *
 * @since 1.8
 * @author Mingle
 */
public class SingletonThree {
	private SingletonThree() {}
	
	private static class SingletonThreeInstance {
		private static final SingletonThree instance = new SingletonThree();
	}
	
	public static SingletonThree getInstance() {
		return SingletonThreeInstance.instance;
	}
}
