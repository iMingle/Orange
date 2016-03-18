/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.composite;

import java.util.Collections;
import java.util.Iterator;

import org.mingle.orange.designpattern.behavioral.visitor.EquipmentVisitor;

/**
 * 设备
 * 
 * @since 1.0
 * @author Mingle
 */
public abstract class Equipment {
    private final String name;

    public Equipment(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public abstract Watt power();
    
    public abstract double netPrice();
    
    public abstract double discountPrice();
    
    /**
     * 访问者模式
     * 
     * @param visitor
     */
    public abstract void accept(EquipmentVisitor visitor);
    
    public void add(Equipment equipment) {
        throw new UnsupportedOperationException();
    }
    
    public void remove(Equipment equipment) {
        throw new UnsupportedOperationException();
    }
    
    @SuppressWarnings("unchecked") public Iterator<Equipment> iterator() {
        return Collections.EMPTY_LIST.iterator();
    }

    public void inventory() {}
}
