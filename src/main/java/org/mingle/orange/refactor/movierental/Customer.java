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
			double thisAccount = 0;
			Rental each = rentals.nextElement();
			
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAccount += 2;
				if (each.getDaysRented() > 2)
					thisAccount += (each.getDaysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAccount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAccount += 1.5;
				if (each.getDaysRented() > 3)
					thisAccount += (each.getDaysRented() - 3) * 1.5;
				break;
			}
			
			// add frequent renter points
			frequentRentalPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
				frequentRentalPoints++;
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAccount) + "\n";
			totalAccount += thisAccount;
		}
		
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAccount) + "\n";
		result += "You earned " + String.valueOf(frequentRentalPoints) + " frequent renter points";
		
		return result;
	}
}
