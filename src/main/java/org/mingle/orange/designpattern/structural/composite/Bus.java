/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

/**
 * 总线
 * 
 * @since 1.0
 * @author Mingle
 */
public class Bus extends CompositeEquipment {

    public Bus(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        return 100;
    }

    @Override public double discountPrice() {
        return 10;
    }
}
