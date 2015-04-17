/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * 方法的测试
 * @since 1.0 2015年4月2日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public class FunctionTest {
	/**
	 * 传递的是引用，对引用的修改相当于对对象的修改
	 * @param l
	 */
	public static void changeValue(Letter l) {
		l.avg = 8.0f;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Letter l = new Letter();
		l.avg = 5.0f;
		FunctionTest.changeValue(l);
		System.out.println(l.avg);				// 8.0
	}

}

class Letter {
	float avg;
}