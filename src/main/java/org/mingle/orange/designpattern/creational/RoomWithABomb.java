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
public class RoomWithABomb extends Room {
    @SuppressWarnings("unused")
    private final Bomb bomb;

    public RoomWithABomb(int no, Bomb bomb) {
        super(no);
        this.bomb = bomb;
    }

}
