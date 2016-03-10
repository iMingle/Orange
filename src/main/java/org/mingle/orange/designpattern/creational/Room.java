/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 房间
 * 
 * @since 1.0
 * @author Mingle
 */
public class Room extends MapSite implements Cloneable {
    @SuppressWarnings("unused")
    private final int no;
    @SuppressWarnings("unused")
    private MapSite[] sides;
    
    public Room(int no) {
        this.no = no;
    }

    @Override
    public void enter() {
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public MapSite getSide(Direction direction) {
        return new Room(0);
    }
    
    public void setSide(Direction direction, MapSite mapSite) {
        
    }
}
