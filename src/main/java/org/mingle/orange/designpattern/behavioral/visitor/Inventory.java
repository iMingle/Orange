/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.visitor;

import org.mingle.orange.designpattern.structural.composite.Equipment;

/**
 * 存货清单
 * 
 * @since 1.0
 * @author Mingle
 */
public class Inventory {

    public void accumulate(Equipment equipment) {
        equipment.inventory();
    }

}
