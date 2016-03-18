/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

import org.mingle.orange.designpattern.behavioral.visitor.EquipmentVisitor;

/**
 * 底盘
 * 
 * @since 1.0
 * @author Mingle
 */
public class Chassis extends CompositeEquipment {

    public Chassis(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        return super.netPrice();
    }

    @Override public double discountPrice() {
        return 5;
    }
    
    @Override public void accept(EquipmentVisitor visitor) {
        visitor.visitChassis(this);
    }
}
