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

import java.util.Random;

import org.orange.java.concurrent.livenessperformance.DynamicOrderDeadlock.Account;
import org.orange.java.concurrent.livenessperformance.DynamicOrderDeadlock.DollarAmount;

/**
 * 循环产生死锁
 * 
 * @author mingle
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rand = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++)
            accounts[i] = new Account();

        class TransferThread extends Thread {
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rand.nextInt(NUM_ACCOUNTS);
                    int toAcct = rand.nextInt(NUM_ACCOUNTS);
                    DollarAmount amount = new DollarAmount(rand.nextInt(1000));
                    try {
                        DynamicOrderDeadlock.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (DynamicOrderDeadlock.InsufficientFundsException ignored) {}
                }
            }
        }
        
        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }
    
}
