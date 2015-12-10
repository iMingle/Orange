/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.construct;

/**
 * 只读适配器
 * 
 * @since 1.8
 * @author Mingle
 */
public class AccountHolder {
	private UpdatableAccount acct = new UpdatableAccountImpl(0);
	private AccountRecorder recorder;

	public AccountHolder(AccountRecorder r) {
		recorder = r;
	}

	public synchronized void acceptMoney(long amount) {
		try {
			acct.credit(amount);
			recorder.recordBalance(new ImmutableAccount(acct));// (*)
		} catch (InsufficientFunds ex) {
			System.out.println("Cannot accept negative amount.");
		}
	}
}

class InsufficientFunds extends Exception {
	private static final long serialVersionUID = -5850295564035875754L;
}

interface Account {
	long balance();
}

interface UpdatableAccount extends Account {
	void credit(long amount) throws InsufficientFunds;

	void debit(long amount) throws InsufficientFunds;
}

// Sample implementation of updatable version
class UpdatableAccountImpl implements UpdatableAccount {
	private long currentBalance;

	public UpdatableAccountImpl(long initialBalance) {
		currentBalance = initialBalance;
	}

	public synchronized long balance() {
		return currentBalance;
	}

	public synchronized void credit(long amount) throws InsufficientFunds {
		if (amount >= 0 || currentBalance >= -amount)
			currentBalance += amount;
		else
			throw new InsufficientFunds();
	}

	public synchronized void debit(long amount) throws InsufficientFunds {
		credit(-amount);
	}
}

final class ImmutableAccount implements Account {
	private Account delegate;

	public ImmutableAccount(long initialBalance) {
		delegate = new UpdatableAccountImpl(initialBalance);
	}

	ImmutableAccount(Account acct) {
		delegate = acct;
	}

	public long balance() { // forward the immutable method
		return delegate.balance();
	}
}

class AccountRecorder { // A logging facility
	public void recordBalance(Account a) {
		System.out.println(a.balance()); // or record in file
	}
}

class EvilAccountRecorder extends AccountRecorder {
	@SuppressWarnings("unused")
	private long embezzlement;

	public void recordBalance(Account a) {
		super.recordBalance(a);

		if (a instanceof UpdatableAccount) {
			UpdatableAccount u = (UpdatableAccount) a;
			try {
				u.debit(10);
				embezzlement += 10;
			} catch (InsufficientFunds quietlyignore) {
			}
		}
	}
}
