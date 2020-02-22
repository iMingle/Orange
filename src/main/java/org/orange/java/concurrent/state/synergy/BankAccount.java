/*
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
 * limitations under the License.
 */

package org.orange.java.concurrent.state.synergy;

/**
 * 协同处理通用解决方案
 * 
 * @author mingle
 */
public class BankAccount {
    protected long balance = 0;

    public synchronized long balance() {
        return balance;
    }

    public synchronized void deposit(long amount) throws InsufficientFunds {
        if (balance + amount < 0)
            throw new InsufficientFunds();
        else
            balance += amount;
    }

    public void withdraw(long amount) throws InsufficientFunds {
        deposit(-amount);
    }
}

class ATCheckingAccount extends BankAccount {
    protected ATSavingsAccount savings;
    protected long threshold;
    protected TSBoolean transferInProgress = new TSBoolean();

    public ATCheckingAccount(long t) {
        threshold = t;
    }

    // called only upon initialization
    synchronized void initSavings(ATSavingsAccount s) {
        savings = s;
    }

    protected boolean shouldTry() {
        return balance < threshold;
    }

    void tryTransfer() { // called internally or from savings
        if (!transferInProgress.testAndSet()) { // if not busy ...
            try {
                synchronized (this) {
                    if (shouldTry())
                        balance += savings.transferOut();
                }
            } finally {
                transferInProgress.clear();
            }
        }
    }

    public synchronized void deposit(long amount) throws InsufficientFunds {
        if (balance + amount < 0)
            throw new InsufficientFunds();
        else {
            balance += amount;
            tryTransfer();
        }
    }
}

class ATSavingsAccount extends BankAccount {
    protected ATCheckingAccount checking;
    protected long maxTransfer;

    public ATSavingsAccount(long max) {
        maxTransfer = max;
    }

    // called only upon initialization
    synchronized void initChecking(ATCheckingAccount c) {
        checking = c;
    }

    synchronized long transferOut() { // called only from checking
        long amount = balance;
        if (amount > maxTransfer)
            amount = maxTransfer;
        if (amount >= 0)
            balance -= amount;
        return amount;
    }

    public synchronized void deposit(long amount) throws InsufficientFunds {
        if (balance + amount < 0)
            throw new InsufficientFunds();
        else {
            balance += amount;
            checking.tryTransfer();
        }
    }

}
