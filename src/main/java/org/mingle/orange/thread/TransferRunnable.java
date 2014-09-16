/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.thread;

/**
 * a runnable that transfers money form an account to other accounts in bank.
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class TransferRunnable implements Runnable {
	private Bank bank;
	private int fromAccount;
	private double maxAccount;
	private int DELAY = 10;

	/**
	 * @param bank
	 * @param from
	 * @param max
	 */
	public TransferRunnable(Bank bank, int from, double max) {
		this.bank = bank;
		this.fromAccount = from;
		this.maxAccount = max;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAccount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((long) (DELAY * Math.random()));
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}
