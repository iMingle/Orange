/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.designpattern.creational.abstractfactory;

import org.mingle.orange.designpattern.creational.Bomb;
import org.mingle.orange.designpattern.creational.BombedWall;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.RoomWithABomb;
import org.mingle.orange.designpattern.creational.Wall;

/**
 * 炸弹迷宫工厂
 * 
 * @since 1.0
 * @author Mingle
 */
public class BombedMazeFactory extends MazeFactory {
    @Override public Wall makeWall() {
        return new BombedWall(new Bomb());
    }
    
    @Override public Room makeRoom(int no) {
        return new RoomWithABomb(no, new Bomb());
    }
}
