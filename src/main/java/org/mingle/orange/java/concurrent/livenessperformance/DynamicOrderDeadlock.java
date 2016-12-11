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

package org.mingle.orange.java.concurrent.livenessperformance;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 动态的锁顺序死锁
 * 
 * @author mingle
 */
public class DynamicOrderDeadlock {

    /**
     * transferMoney(A, B, ...)与transferMoney(B, A, ...)通知调用
     * 
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @throws InsufficientFundsException
     */
    public static void transferMoney(Account fromAccount, Account toAccount,
            DollarAmount amount) throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }

    static class DollarAmount implements Comparable<DollarAmount> {
        public DollarAmount(int amount) {
        }

        public DollarAmount add(DollarAmount d) {
            return null;
        }

        public DollarAmount subtract(DollarAmount d) {
            return null;
        }

        public int compareTo(DollarAmount dollarAmount) {
            return 0;
        }
    }

    static class Account {
        private DollarAmount balance = new DollarAmount(10);
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {
        private static final long serialVersionUID = -5299531663695472828L;
    }
}
