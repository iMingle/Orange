/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.concurrent.livenessperformance;

import java.util.Random;

import org.mingle.orange.concurrent.livenessperformance.DynamicOrderDeadlock.Account;
import org.mingle.orange.concurrent.livenessperformance.DynamicOrderDeadlock.DollarAmount;

/**
 * 循环产生死锁
 * 
 * @since 1.8
 * @author Mingle
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
