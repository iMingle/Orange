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

package org.mingle.orange.java.concurrent.construct;

/**
 * 只读适配器
 * 
 * @author mingle
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
