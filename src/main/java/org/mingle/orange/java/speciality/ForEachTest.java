/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * foreach测试
 * 
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class ForEachTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = new int[] { 6, 8 };
		for (int i = 0; i < array.length; i++) {
			System.out.println(i);
		}
		
		for (int i : array) {
			System.out.println(i);
		}
	}

}
