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

package org.mingle.orange.designpattern.creational.builder;

import org.mingle.orange.designpattern.creational.Maze;

/**
 * 迷宫游戏,用于创建迷宫
 * 
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
