/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.refactor.movierental;

/**
 * 租赁
 * 
 * @since 1.0
 * @author Mingle
 */
public class Rental {
    private Movie movie;
    private int daysRented;
    
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
