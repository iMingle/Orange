/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.builder;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public interface MazeBuilder<T> {
    void buildMaze();
    void buildRoom(int no);
    void buildDoor(int from, int to);
    T getMaze();
}
