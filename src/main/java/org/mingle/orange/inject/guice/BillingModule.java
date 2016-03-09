/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.inject.guice;

import com.google.inject.AbstractModule;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class BillingModule extends AbstractModule {

    @Override
    protected void configure() {
        /*
         * This tells Guice that whenever it sees a dependency on a
         * TransactionLog, it should satisfy the dependency using a
         * DatabaseTransactionLog.
         */
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);

        /*
         * Similarly, this binding tells Guice that when CreditCardProcessor is
         * used in a dependency, that should be satisfied with a
         * PaypalCreditCardProcessor.
         */
        bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
    }

}
