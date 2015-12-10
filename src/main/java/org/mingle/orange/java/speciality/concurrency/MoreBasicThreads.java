/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 多个基本线程同时运行
 *
 * @since 1.8
 * @author Mingle
 */
public class MoreBasicThreads {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new Thread(new LiftOff()).start();
		System.out.println("Waiting for LiftOff");
	}

}
