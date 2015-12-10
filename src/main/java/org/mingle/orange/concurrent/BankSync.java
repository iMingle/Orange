/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent;

/**
 * a bank with a number of bank accounts that uses synchronization primitives.
 * 
 * @since 1.8
 * @author Mingle
 */
public class BankSync {
	private final double[] accounts;

	/**
	 * @param accounts
	 */
	public BankSync(int n, double initialBalance) {
		this.accounts = new double[n];
		for (int i = 0; i < accounts.length; i++)
			accounts[i] = initialBalance;
	}
	
	/**
	 * transfers money from one account to another.
	 * @param from
	 * @param to
	 * @param account
	 * @throws InterruptedException 
	 */
	public synchronized void transfer(int from, int to, double account) throws InterruptedException {
		while (accounts[from] < account) 
			// 线程等待
			this.wait();
		System.out.print(Thread.currentThread());
		accounts[from] -= account;
		System.out.printf(" %10.2f from %d to %d", account, from, to);
		accounts[to] += account;
		System.out.printf(" Total Balance: %10.2f\n", this.getTotalBalance());
		// 激活所有等待的线程
		this.notifyAll();
	}

	/**
	 * gets the sum of all account balances.
	 * @return
	 */
	public synchronized double getTotalBalance() {
		double sum = 0;
		for (double a : accounts)
			sum += a;
		return sum;
	}
	
	/**
	 * gets the number of accounts in the bank.
	 * @return
	 */
	public int size() {
		return accounts.length;
	}
}
