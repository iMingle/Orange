/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.concurrent.state.transaction;

import org.mingle.orange.java.concurrent.state.synergy.InsufficientFunds;

/**
 * 转账的事务控制
 * 
 * @since 1.0
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
