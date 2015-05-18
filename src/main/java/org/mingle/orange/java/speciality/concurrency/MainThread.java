/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 运行main线程
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class MainThread {

	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}

}
