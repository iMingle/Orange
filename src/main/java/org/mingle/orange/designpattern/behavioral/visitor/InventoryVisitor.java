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
 * 存货清单
 * 
 * @since 1.0
 * @author Mingle
 */
public class InventoryVisitor implements EquipmentVisitor {
    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    @Override public void visitFloppyDisk(FloppyDisk floppyDisk) {
        inventory.accumulate(floppyDisk);
    }

    @Override public void visitCard(Card card) {
        inventory.accumulate(card);
    }

    @Override public void visitChassis(Chassis chassis) {
        inventory.accumulate(chassis);
    }

    @Override public void visitBus(Bus bus) {
        inventory.accumulate(bus);
    }

    @Override public void visitCabinet(Cabinet cabinet) {
        inventory.accumulate(cabinet);
    }

}
