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

package org.mingle.orange.refactor;

import org.junit.Test;
import org.mingle.orange.refactor.movierental.Customer;
import org.mingle.orange.refactor.movierental.Movie;
import org.mingle.orange.refactor.movierental.Rental;
import org.mockito.Mockito;

/**
 * 
 * 
 * @author mingle
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
