/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class BombedWall extends Wall {
    @SuppressWarnings("unused")
    private final Bomb bomb;
    
    public BombedWall(Bomb bomb) {
        this.bomb = bomb;
    }
}
