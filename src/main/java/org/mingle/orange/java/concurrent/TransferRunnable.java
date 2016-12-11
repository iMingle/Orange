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

package org.mingle.orange.java.concurrent;

/**
 * a runnable that transfers money form an account to other accounts in bank.
 * @author mingle
 */
public class TransferRunnable implements Runnable {
    private Bank bank;
    private int fromAccount;
    private double maxAccount;
    private int DELAY = 10;

    /**
     * @param bank
     * @param from
     * @param max
     */
    public TransferRunnable(Bank bank, int from, double max) {
        this.bank = bank;
        this.fromAccount = from;
        this.maxAccount = max;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAccount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((long) (DELAY * Math.random()));
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }

}
