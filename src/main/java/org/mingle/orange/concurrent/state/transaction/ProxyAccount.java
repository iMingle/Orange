/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.state.transaction;

/**
 * 代理账户
 * 
 * @since 1.8
 * @author Mingle
 */
public class ProxyAccount {
	private TransBankAccount delegate;

	public boolean join(Transaction t) {
		return delegate.join(t);
	}

	public long balance(Transaction t) throws Failure {
		return delegate.balance(t);
	}
}
