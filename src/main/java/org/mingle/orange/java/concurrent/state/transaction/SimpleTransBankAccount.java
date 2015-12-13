/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.transaction;

import org.mingle.orange.java.concurrent.state.synergy.InsufficientFunds;

/**
 * 转账的事务控制
 * 
 * @since 1.8
 * @author Mingle
 */
public class SimpleTransBankAccount implements TransBankAccount {

	protected long balance = 0;
	protected long workingBalance = 0; // single shadow copy
	protected Transaction currentTx = null; // single transaction

	@Override
	public synchronized long balance(Transaction t) throws Failure {
		if (t != currentTx)
			throw new Failure();
		return workingBalance;
	}

	@Override
	public synchronized void deposit(Transaction t, long amount)
			throws InsufficientFunds, Failure {
		if (t != currentTx)
			throw new Failure();
		if (workingBalance < -amount)
			throw new InsufficientFunds();
		workingBalance += amount;
	}

	@Override
	public synchronized void withdraw(Transaction t, long amount)
			throws InsufficientFunds, Failure {
		deposit(t, -amount);
	}

	@Override
	public synchronized boolean join(Transaction t) {
		if (currentTx != null)
			return false;
		currentTx = t;
		workingBalance = balance;
		return true;
	}

	@Override
	public synchronized boolean canCommit(Transaction t) {
		return (t == currentTx);
	}

	@Override
	public synchronized void abort(Transaction t) {
		if (t == currentTx)
			currentTx = null;
	}

	@Override
	public synchronized void commit(Transaction t) throws Failure {
		if (t != currentTx)
			throw new Failure();
		balance = workingBalance;
		currentTx = null;
	}
}
