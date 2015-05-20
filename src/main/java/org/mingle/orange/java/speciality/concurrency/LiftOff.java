/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 火箭发射前的倒计时
 *
 * @since 1.8
 * @author Mingle
 */
public class LiftOff implements Runnable {
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;
	
	public LiftOff() {}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}

}
