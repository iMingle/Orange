/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.builder;

/**
 * 迷宫构件计数器
 * 
 * @since 1.0
 * @author Mingle
 */
public class CountingMazeBuilder extends DefaultMazeBuilder {
    private int doors;
    private int rooms;

    public int doors() {
        return doors;
    }

    public int rooms() {
        return rooms;
    }

    @Override
    public void buildRoom(int no) {
        super.buildRoom(no);
        rooms++;
    }

    @Override
    public void buildDoor(int from, int to) {
        super.buildDoor(from, to);
        doors++;
    }
}
