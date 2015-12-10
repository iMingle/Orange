/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

/**
 * 创建和销毁对象
 * 
 * @since 1.8
 * @author Mingle
 */
public class CreateAndDestroyObject {
	
	/**
	 * 考虑用静态工厂代替构造器
	 * 
	 * @param b
	 * @return
	 */
	public static Boolean valueOf(boolean b) {
		return b ? Boolean.TRUE : Boolean.FALSE;
	}
	
}
