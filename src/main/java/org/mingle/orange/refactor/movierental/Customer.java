/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.refactor.movierental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客
 * 
 * @since 1.8
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
		double totalAccount = 0;
		int frequentRentalPoints = 0;
		Enumeration<Rental> rentals = this.rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			
			frequentRentalPoints = each.getFrequentRentalPoints();
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
			totalAccount += each.getCharge();
		}
		
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAccount) + "\n";
		result += "You earned " + String.valueOf(frequentRentalPoints) + " frequent renter points";
		
		return result;
	}
}
