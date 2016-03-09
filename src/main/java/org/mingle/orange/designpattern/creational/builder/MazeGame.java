/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.creational.builder;

import org.mingle.orange.designpattern.creational.Maze;

/**
 * 迷宫游戏,用于创建迷宫
 * 
 * @since 1.0
 * @author Mingle
 */
public class MazeGame {
    public Maze createMaze(MazeBuilder<Maze> builder) {
        builder.buildMaze();
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildDoor(1, 2);
        
        return builder.getMaze();
    }
    
    public Maze createComplexMaze(MazeBuilder<Maze> builder) {
        builder.buildMaze();
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildRoom(3);
        builder.buildRoom(4);
        builder.buildDoor(1, 2);
        builder.buildDoor(2, 3);
        builder.buildDoor(3, 4);
        
        return builder.getMaze();
    }
}
