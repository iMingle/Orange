/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.prototype;

import org.mingle.orange.designpattern.creational.Direction;
import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;

/**
 * 迷宫游戏,用于创建迷宫
 * 
 * @since 1.0
 * @author Mingle
 */
public class MazeGame {
    
    public Maze createMaze(MazePrototypeFactory prototype) {
        Maze maze = prototype.makeMaze();
        Room r1 = prototype.makeRoom(1);
        Room r2 = prototype.makeRoom(2);
        Door door = prototype.makeDoor(r1, r2);
        maze.addRoom(r1);
        maze.addRoom(r2);
        
        r1.setSide(Direction.NORTH, prototype.makeWall());
        r1.setSide(Direction.EAST, door);
        r1.setSide(Direction.SOUTH, prototype.makeWall());
        r1.setSide(Direction.WEST, prototype.makeWall());
        
        r2.setSide(Direction.NORTH, prototype.makeWall());
        r2.setSide(Direction.EAST, prototype.makeWall());
        r2.setSide(Direction.SOUTH, prototype.makeWall());
        r2.setSide(Direction.WEST, door);
        
        return maze;
    }
}
