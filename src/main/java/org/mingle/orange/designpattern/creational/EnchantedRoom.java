/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 魔法房间
 * 
 * @since 1.0
 * @author Mingle
 */
public class EnchantedRoom extends Room {
    @SuppressWarnings("unused")
    private final Spell spell;
    
    public EnchantedRoom(int no, Spell spell) {
        super(no);
        this.spell = spell;
    }

}
