/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
