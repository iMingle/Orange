/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

/**
 * 磁盘
 * 
 * @since 1.0
 * @author Mingle
 */
public class FloppyDisk extends Equipment {

    public FloppyDisk(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        return 30;
    }

    @Override public double discountPrice() {
        return 3;
    }

}
