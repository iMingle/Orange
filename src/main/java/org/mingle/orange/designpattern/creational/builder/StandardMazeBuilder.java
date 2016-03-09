/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.builder;

import org.mingle.orange.designpattern.creational.Direction;
import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Wall;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class StandardMazeBuilder implements MazeBuilder<Maze> {
    private Maze current;

    @Override public void buildMaze() {
        current = new Maze();
    }

    @Override public void buildRoom(int no) {
        if (current.getRoom(no) == null) {
            Room room = new Room(no);
            room.setSide(Direction.NORTH, new Wall());
            room.setSide(Direction.SOUTH, new Wall());
            room.setSide(Direction.EAST, new Wall());
            room.setSide(Direction.WEST, new Wall());
            current.addRoom(room);
        }
    }

    @Override public void buildDoor(int from, int to) {
        Room r1 = current.getRoom(from);
        Room r2 = current.getRoom(to);
        Door door = new Door(r1, r2);
        r1.setSide(commonWall(r1, r2), door);
        r2.setSide(commonWall(r2, r1), door);
    }

    @Override public Maze getMaze() {
        return current;
    }

    /**
     * 决定两个房间之间的公共墙壁的方位
     * 
     * @param r1
     * @param r2
     * @return
     */
    private Direction commonWall(Room r1, Room r2) {
        return Direction.NORTH;
    }
}
