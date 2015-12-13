/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bank with a number of bank accounts.
 * @since 1.8
 * @author Mingle
 */
public class Bank {
	private final double[] accounts;
	private Lock bankLock = new ReentrantLock();
	// 充足的资金
	private Condition sufficientFunds;

	/**
	 * @param accounts
	 */
	public Bank(int n, double initialBalance) {
		this.accounts = new double[n];
		for (int i = 0; i < accounts.length; i++)
			accounts[i] = initialBalance;
		this.bankLock = new ReentrantLock();
		this.sufficientFunds = this.bankLock.newCondition();
	}
	
	/**
	 * transfers money from one account to another.
	 * @param from
	 * @param to
	 * @param account
	 * @throws InterruptedException 
	 */
	public void transfer(int from, int to, double account) throws InterruptedException {
		bankLock.lock();
		try {
			while (accounts[from] < account) 
				// 线程等待
				sufficientFunds.await();
			System.out.print(Thread.currentThread());
			accounts[from] -= account;
			System.out.printf(" %10.2f from %d to %d", account, from, to);
			accounts[to] += account;
			System.out.printf(" Total Balance: %10.2f\n", this.getTotalBalance());
			// 激活所有等待的线程
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
	}

	/**
	 * gets the sum of all account balances.
	 * @return
	 */
	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double a : accounts)
				sum += a;
			return sum;
		} finally {
			bankLock.unlock();
		}
	}
	
	/**
	 * gets the number of accounts in the bank.
	 * @return
	 */
	public int size() {
		return accounts.length;
	}
}
