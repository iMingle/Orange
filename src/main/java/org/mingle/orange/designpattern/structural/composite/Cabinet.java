/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

/**
 * 柜子
 * 
 * @since 1.0
 * @author Mingle
 */
public class Cabinet extends CompositeEquipment {

    public Cabinet(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        return 50;
    }

    @Override public double discountPrice() {
        return 2;
    }
}
