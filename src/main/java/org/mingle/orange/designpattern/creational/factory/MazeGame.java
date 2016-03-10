/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.factory;

import org.mingle.orange.designpattern.creational.Direction;
import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Wall;

/**
 * 迷宫游戏,用于创建迷宫
 * 
 * @since 1.0
 * @author Mingle
 */
public class MazeGame {
    public Maze makeMaze() {
        return new Maze();
    }
    
    public Wall makeWall() {
        return new Wall();
    }
    
    public Room makeRoom(int no) {
        return new Room(no);
    }
    
    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
    
    public Maze createMaze() {
        Maze maze = makeMaze();
        Room r1 = makeRoom(1);
        Room r2 = makeRoom(2);
        Door door = makeDoor(r1, r2);
        maze.addRoom(r1);
        maze.addRoom(r2);
        
        r1.setSide(Direction.NORTH, makeWall());
        r1.setSide(Direction.EAST, door);
        r1.setSide(Direction.SOUTH, makeWall());
        r1.setSide(Direction.WEST, makeWall());
        
        r2.setSide(Direction.NORTH, makeWall());
        r2.setSide(Direction.EAST, makeWall());
        r2.setSide(Direction.SOUTH, makeWall());
        r2.setSide(Direction.WEST, door);
        
        return maze;
    }
}
