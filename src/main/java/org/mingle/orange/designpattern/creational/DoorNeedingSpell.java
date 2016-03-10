/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 魔法门
 * 
 * @since 1.0
 * @author Mingle
 */
public class DoorNeedingSpell extends Door implements Cloneable {

    public DoorNeedingSpell(Room room1, Room room2) {
        super(room1, room2);
    }

    @Override public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
