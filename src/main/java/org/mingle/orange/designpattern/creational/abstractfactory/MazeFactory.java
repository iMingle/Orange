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

import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.Maze;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Wall;

/**
 * 迷宫工厂
 *
 * @author mingle
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
