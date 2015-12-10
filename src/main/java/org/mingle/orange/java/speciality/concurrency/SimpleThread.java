/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 继承Thread的线程
 *
 * @since 1.8
 * @author Mingle
 */
public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	
	public SimpleThread() {
		super(Integer.toString(++threadCount));
		start();
	}
	
	public String toString() {
		return "#" + getName() + "(" + countDown + "), "; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}

}
