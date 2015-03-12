/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

/**
 * This program shows data corruption when multiple threads access a data structure.
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class UnsynchBankTest {
	private static final int NACCOUNTS = 100;
	private static final double INITIAL_BALANCE = 1000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
		
		for (int i = 0; i < NACCOUNTS; i++) {
			TransferRunnable r = new TransferRunnable(bank, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

}
