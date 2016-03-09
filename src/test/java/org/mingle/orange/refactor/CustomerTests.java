/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.refactor;

import org.junit.Test;
import org.mingle.orange.refactor.movierental.Customer;
import org.mingle.orange.refactor.movierental.Movie;
import org.mingle.orange.refactor.movierental.Rental;
import org.mockito.Mockito;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class CustomerTests {
    @Test
    public void statement() {
        System.out.println(Mockito.mock(Customer.class));
        Customer customer = new Customer("Mingle");
        customer.addRental(new Rental(new Movie("末日", Movie.REGULAR), 7));
        System.out.println(customer.statement());
        System.out.println(customer.htmlStatement());
    }
}
