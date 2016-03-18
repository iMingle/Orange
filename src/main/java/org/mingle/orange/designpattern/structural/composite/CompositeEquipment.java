/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mingle.orange.designpattern.behavioral.visitor.EquipmentVisitor;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class CompositeEquipment extends Equipment {
    private List<Equipment> equipments = new ArrayList<>();

    public CompositeEquipment(String name) {
        super(name);
    }

    @Override public Watt power() {
        return null;
    }

    @Override public double netPrice() {
        Iterator<Equipment> iterator = iterator();
        double total = 0;
        
        while (iterator.hasNext())
            total += iterator.next().netPrice();
        
        return total;
    }

    @Override public double discountPrice() {
        return 0;
    }

    @Override public void add(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override public void remove(Equipment equipment) {
        equipments.remove(equipment);
    }

    @Override public Iterator<Equipment> iterator() {
        return equipments.iterator();
    }

    @Override public void accept(EquipmentVisitor visitor) {
        Iterator<Equipment> it = iterator();
        while (it.hasNext())
            it.next().accept(visitor);
    }

}
