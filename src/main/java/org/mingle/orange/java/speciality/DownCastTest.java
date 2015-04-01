/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality;

/**
 * 向下转型测试
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class DownCastTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Son s = (Son) new Father(48);			// cannot be cast
		s.getAge();
	}

}

