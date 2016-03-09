/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state.transaction;

/**
 * 参与者实现的接口
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Transactor {
    // Enter a new transaction and return true, if can do so
    public boolean join(Transaction t);

    // Return true if this transaction can be committed
    public boolean canCommit(Transaction t);

    // Update state to reflect current transaction
    public void commit(Transaction t) throws Failure;

    // Roll back state (No exception; ignore if inapplicable)
    public void abort(Transaction t);
}
