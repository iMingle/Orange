/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

}
