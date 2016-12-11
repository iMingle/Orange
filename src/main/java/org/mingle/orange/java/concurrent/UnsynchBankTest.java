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

package org.mingle.orange.java.concurrent;

/**
 * This program shows data corruption when multiple threads access a data structure.
 * @author mingle
 */
public class UnsynchBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;

    /**
     * @param args
     */
    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(bank, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }

}
