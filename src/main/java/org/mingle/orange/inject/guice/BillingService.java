/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.inject.guice;

import javax.inject.Inject;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class BillingService {
    @SuppressWarnings("unused")
    private final CreditCardProcessor processor;
    @SuppressWarnings("unused")
    private final TransactionLog transactionLog;

    @Inject
    BillingService(CreditCardProcessor processor, TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        return new Receipt();
    }
}
