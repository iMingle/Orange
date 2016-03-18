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
 * 设备访问者
 * 
 * @since 1.0
 * @author Mingle
 */
public interface EquipmentVisitor {
    void visitFloppyDisk(FloppyDisk floppyDisk);
    void visitCard(Card card);
    void visitChassis(Chassis chassis);
    void visitBus(Bus bus);
    void visitCabinet(Cabinet cabinet);
}
