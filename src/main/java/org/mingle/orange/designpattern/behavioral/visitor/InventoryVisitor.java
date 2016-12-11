/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
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
 * @author mingle
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
