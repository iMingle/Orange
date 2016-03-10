/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

/**
 * 内存卡
 * 
 * @since 1.0
 * @author Mingle
 */
public class Card extends Equipment {

    public Card(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        return 30;
    }

    @Override public double discountPrice() {
        return 5;
    }

}
