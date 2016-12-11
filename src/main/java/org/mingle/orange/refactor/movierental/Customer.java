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

package org.mingle.orange.refactor.movierental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客
 * 
 * @since 1.0
 * @author Mingle
 */
public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();
    
    public Customer(String name) {
        this.name = name;
    }
    
    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }
    
    /**
     * 生成订单
     * 
     * @return
     */
    public String statement() {
        Enumeration<Rental> rentals = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getMovie().getPrice().getCharge(each.getDaysRented())) + "\n";
        }
        
        // add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRentalPoints()) + " frequent renter points";
        
        return result;
    }
    
    /**
     * 生成HTML订单
     * 
     * @return
     */
    public String htmlStatement() {
        Enumeration<Rental> rentals = this.rentals.elements();
        String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            
            // show figures for this rental
            result += each.getMovie().getTitle() + ": " + String.valueOf(each.getMovie().getPrice().getCharge(each.getDaysRented())) + "<BR>\n";
        }
        
        // add footer lines
        result += "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" + String.valueOf(getTotalFrequentRentalPoints()) + "</EM> frequent renter points<P>";
        
        return result;
    }

    private int getTotalFrequentRentalPoints() {
        int result = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            
            result += each.getMovie().getFrequentRentalPoints(each.getDaysRented());
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            
            result += each.getMovie().getPrice().getCharge(each.getDaysRented());
        }
        return result;
    }
}
