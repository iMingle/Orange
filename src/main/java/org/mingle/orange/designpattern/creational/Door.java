/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 门
 * 
 * @since 1.0
 * @author Mingle
 */
public class Door extends MapSite implements Cloneable {
    @SuppressWarnings("unused")
    private final Room room1;
    @SuppressWarnings("unused")
    private final Room room2;
    @SuppressWarnings("unused")
    private boolean isOpen;

    public Door(Room room1, Room room2) {
        super();
        this.room1 = room1;
        this.room2 = room2;
    }

    @Override
    public void enter() {
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Room otherSideFrom(Room from) {
        return new Room(0);
    }
}
