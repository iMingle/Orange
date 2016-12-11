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

package org.mingle.orange.designpattern.creational.prototype;

import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Wall;
import org.mingle.orange.designpattern.creational.abstractfactory.MazeFactory;

/**
 * 迷宫原型工厂
 * 
 * @author mingle
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
