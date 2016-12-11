/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mingle.orange.designpattern.creational.abstractfactory;

import org.mingle.orange.designpattern.creational.Direction;
import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;

/**
 * 迷宫游戏,用于创建迷宫
 * 
 * @author mingle
 */
public class MazeGame {
    public Maze createMaze(AbstractMazeFactory factory) {
        Maze maze = factory.makeMaze();
        Room r1 = factory.makeRoom(1);
        Room r2 = factory.makeRoom(2);
        Door door = factory.makeDoor(r1, r2);
        maze.addRoom(r1);
        maze.addRoom(r2);
        
        r1.setSide(Direction.NORTH, factory.makeWall());
        r1.setSide(Direction.EAST, door);
        r1.setSide(Direction.SOUTH, factory.makeWall());
        r1.setSide(Direction.WEST, factory.makeWall());
        
        r2.setSide(Direction.NORTH, factory.makeWall());
        r2.setSide(Direction.EAST, factory.makeWall());
        r2.setSide(Direction.SOUTH, factory.makeWall());
        r2.setSide(Direction.WEST, door);
        
        return maze;
    }
}
