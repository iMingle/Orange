/**
 * Copyright (c) 2015, Mingle. All rights reserved.
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
		
		for (int i : array) {
			System.out.println(i);
		}
	}

}
