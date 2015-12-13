/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.transaction;

import org.mingle.orange.java.concurrent.state.synergy.InsufficientFunds;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public interface TransBankAccount extends Transactor {
	public long balance(Transaction t) throws Failure;

	public void deposit(Transaction t, long amount) throws InsufficientFunds,
			Failure;

	public void withdraw(Transaction t, long amount) throws InsufficientFunds,
			Failure;
}
