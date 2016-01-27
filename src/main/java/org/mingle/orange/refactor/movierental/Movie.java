/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.refactor.movierental;

/**
 * 影片
 * 
 * @since 1.8
 * @author Mingle
 */
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    
    private String title;
    private Price price;
    
    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int priceCode) {
        switch (priceCode) {
        case Movie.REGULAR:
            price = new RegularPrice();
            break;
        case Movie.NEW_RELEASE:
            price = new NewRealeasePrice();
            break;
        case Movie.CHILDRENS:
            price = new ChildrensPrice();
            break;
        default:
            throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRentalPoints(int daysRented) {
        return price.getFrequentRentalPoints(daysRented);
    }
}

abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    public int getFrequentRentalPoints(int daysRented) {
        return 1;
    }
}

class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }
    
    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
    
}

class NewRealeasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
    
    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }
    
    @Override
    public int getFrequentRentalPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}

class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }
    
    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
    
}