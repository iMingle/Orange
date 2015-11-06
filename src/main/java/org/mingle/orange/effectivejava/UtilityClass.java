/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

/**
 * 工具类
 * 
 * @since 1.8
 * @author Mingle
 */
public class UtilityClass {
	/**
	 * 通过私有构造器强化不可实例化的能力
	 */
	private UtilityClass() {
		throw new AssertionError();
	}
}
