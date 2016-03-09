/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.abstractfactory;

import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Wall;

/**
 * 迷宫工厂
 * 
 * @since 1.0
 * @author Mingle
 */
public class MazeFactory implements AbstractMazeFactory {
    @Override public Maze makeMaze() {
        return new Maze();
    }
    
    @Override public Wall makeWall() {
        return new Wall();
    }
    
    @Override public Room makeRoom(int no) {
        return new Room(no);
    }
    
    @Override public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}
