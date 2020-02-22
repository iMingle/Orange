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

package org.orange.java.concurrent.livenessperformance;

/**
 * 通过锁顺序来避免死锁
 * 
 * @author mingle
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
