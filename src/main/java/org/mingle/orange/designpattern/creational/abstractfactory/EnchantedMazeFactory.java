/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.abstractfactory;

import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.DoorNeedingSpell;
import org.mingle.orange.designpattern.creational.EnchantedRoom;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Spell;

/**
 * 魔法迷宫工厂
 * 
 * @since 1.0
 * @author Mingle
 */
public class EnchantedMazeFactory extends MazeFactory {
    @Override public Room makeRoom(int no) {
        return new EnchantedRoom(no, new Spell() {
        });
    }
    
    @Override public Door makeDoor(Room r1, Room r2) {
        return new DoorNeedingSpell(r1, r2);
    }
}
