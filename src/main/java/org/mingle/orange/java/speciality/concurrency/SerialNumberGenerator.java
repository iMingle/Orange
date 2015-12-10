/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 产生序列数字
 *
 * @since 1.8
 * @author Mingle
 */
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	
	public static int nextSerialNumber() {
		return serialNumber++;	// 非线程安全的
	}

}
