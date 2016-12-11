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
 * 
 * 
 * @author mingle
 */
public class AccountUser {
    TransactionLogger log; // a made-up class

    // helper method called on any failure
    void rollback(Transaction t, long amount, TransBankAccount src,
            TransBankAccount dst) {
        log.cancelLogEntry(t, amount, src, dst);
        src.abort(t);
        dst.abort(t);
    }

    public boolean transfer(long amount, TransBankAccount src,
            TransBankAccount dst) throws FailedTransferException,
            RetryableTransferException {

        if (src == null || dst == null) // screen arguments
            throw new IllegalArgumentException();
        if (src == dst)
            return true; // avoid aliasing

        Transaction t = new Transaction();
        log.logTransfer(t, amount, src, dst); // record

        if (!src.join(t) || !dst.join(t)) { // cannot join
            rollback(t, amount, src, dst);
            throw new RetryableTransferException();
        }

        try {
            src.withdraw(t, amount);
            dst.deposit(t, amount);
        } catch (InsufficientFunds ex) { // semantic failure
            rollback(t, amount, src, dst);
            return false;
        } catch (Failure k) { // transaction error
            rollback(t, amount, src, dst);
            throw new RetryableTransferException();
        }

        if (!src.canCommit(t) || !dst.canCommit(t)) { // interference
            rollback(t, amount, src, dst);
            throw new RetryableTransferException();
        }

        try {
            src.commit(t);
            dst.commit(t);
            log.logCompletedTransfer(t, amount, src, dst);
            return true;
        } catch (Failure k) { // commitment failure
            rollback(t, amount, src, dst);
            throw new FailedTransferException();
        }

    }
}
