/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.livenessperformance;

/**
 * 通过锁顺序来避免死锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class InduceLockOrder {
	private static final Object tieLock = new Object();

	public void transferMoney(Account fromAccount, Account toAccount,
			DollarAmount amount) throws InsufficientFundsException {
		class Helper {
			public void transfer() throws InsufficientFundsException {
				if (fromAccount.getBalance().compareTo(amount) < 0)
					throw new InsufficientFundsException();
				else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}

		int fromHash = System.identityHashCode(fromAccount);
		int toHash = System.identityHashCode(toAccount);

		if (fromHash < toHash) {
			synchronized (fromAccount) {
				synchronized (toAccount) {
					new Helper().transfer();
				}
			}
		} else if (fromHash > toHash) {
			synchronized (toAccount) {
				synchronized (fromAccount) {
					new Helper().transfer();
				}
			}
		} else {
			synchronized (tieLock) {
				synchronized (fromAccount) {
					synchronized (toAccount) {
						new Helper().transfer();
					}
				}
			}
		}
	}

	interface DollarAmount extends Comparable<DollarAmount> {
	}

	interface Account {
		void debit(DollarAmount d);

		void credit(DollarAmount d);

		DollarAmount getBalance();

		int getAcctNo();
	}

	class InsufficientFundsException extends Exception {
		private static final long serialVersionUID = -1513455755958604625L;
	}
}
