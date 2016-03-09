/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 房间集合
 * 
 * @since 1.0
 * @author Mingle
 */
public class Maze {
    public void addRoom(Room room) {
        
    }
    
    public Room getRoom(int roomNo) {
        return new Room(roomNo);
    }
}
