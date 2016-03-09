/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.abstractfactory;

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
public interface AbstractMazeFactory {

    Maze makeMaze();

    Wall makeWall();

    Room makeRoom(int no);

    Door makeDoor(Room r1, Room r2);

}