/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.prototype;

import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Wall;
import org.mingle.orange.designpattern.creational.abstractfactory.MazeFactory;

/**
 * 迷宫原型工厂
 * 
 * @since 1.0
 * @author Mingle
 */
public class MazePrototypeFactory extends MazeFactory {
    /**
     * 原型对象
     */
    private final Maze maze;
    private final Wall wall;
    private final Room room;
    private final Door door;
    
    public MazePrototypeFactory(Maze maze, Wall wall, Room room, Door door) {
        super();
        this.maze = maze;
        this.wall = wall;
        this.room = room;
        this.door = door;
    }

    @Override public Maze makeMaze() {
        Maze cloned = null;
        try {
            cloned = (Maze) maze.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
    
    @Override public Wall makeWall() {
        Wall cloned = null;
        try {
            cloned = (Wall) wall.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
    
    @Override public Room makeRoom(int no) {
        Room cloned = null;
        try {
            cloned = (Room) room.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
    
    @Override public Door makeDoor(Room r1, Room r2) {
        Door cloned = null;
        try {
            cloned = (Door) door.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
}
