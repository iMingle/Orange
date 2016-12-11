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

import org.mingle.orange.designpattern.creational.Door;
import org.mingle.orange.designpattern.creational.DoorNeedingSpell;
import org.mingle.orange.designpattern.creational.EnchantedRoom;
import org.mingle.orange.designpattern.creational.Room;
import org.mingle.orange.designpattern.creational.Spell;

/**
 * 魔法迷宫工厂
 * 
 * @author mingle
 */
public class EnchantedMazeFactory extends MazeFactory {
    private final Spell spell;
    
    public EnchantedMazeFactory(Spell spell) {
        super();
        this.spell = spell;
    }
    
    @Override public Room makeRoom(int no) {
        return new EnchantedRoom(no, spell);
    }
    
    @Override public Door makeDoor(Room r1, Room r2) {
        return new DoorNeedingSpell(r1, r2);
    }
}
