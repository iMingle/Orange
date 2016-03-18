/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.visitor;

import org.mingle.orange.designpattern.structural.composite.Bus;
import org.mingle.orange.designpattern.structural.composite.Cabinet;
import org.mingle.orange.designpattern.structural.composite.Card;
import org.mingle.orange.designpattern.structural.composite.Chassis;
import org.mingle.orange.designpattern.structural.composite.FloppyDisk;

/**
 * 计算设备结构的价格
 * 
 * @since 1.0
 * @author Mingle
 */
public class PricingVisitor implements EquipmentVisitor {
    private double total;

    public double getTotal() {
        return total;
    }

    @Override public void visitFloppyDisk(FloppyDisk floppyDisk) {
        total += floppyDisk.netPrice();
    }

    @Override public void visitCard(Card card) {
        total += card.netPrice();
    }

    @Override public void visitChassis(Chassis chassis) {
        total += chassis.netPrice();
    }

    @Override public void visitBus(Bus bus) {
        total += bus.netPrice();
    }

    @Override public void visitCabinet(Cabinet cabinet) {
        total += cabinet.netPrice();
    }

}
