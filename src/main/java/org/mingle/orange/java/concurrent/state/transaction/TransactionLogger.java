/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.transaction;

/**
 * 事务日志
 * 
 * @since 1.8
 * @author Mingle
 */
public class TransactionLogger {
	void cancelLogEntry(Transaction t, long amount, TransBankAccount src,
			TransBankAccount dst) {
	}

	void logTransfer(Transaction t, long amount, TransBankAccount src,
			TransBankAccount dst) {
	}

	void logCompletedTransfer(Transaction t, long amount, TransBankAccount src,
			TransBankAccount dst) {
	}
}
