/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.builder;

import org.mingle.orange.designpattern.creational.Maze;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class DefaultMazeBuilder implements MazeBuilder<Maze> {

    @Override public void buildMaze() {}

    @Override public void buildRoom(int no) {}

    @Override public void buildDoor(int from, int to) {}

    @Override public Maze getMaze() {
        return new Maze();
    }

}
