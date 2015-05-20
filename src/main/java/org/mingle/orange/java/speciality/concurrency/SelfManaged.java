/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 自管理的线程
 *
 * @since 1.8
 * @author Mingle
 */
public class SelfManaged implements Runnable {
	private int countDown = 5;
	private Thread t = new Thread(this);
	
	public SelfManaged() {
		t.start();
	}
	
	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + "), ";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
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
		for (int i = 0; i < 5; i++)
			new SelfManaged();
	}

}
