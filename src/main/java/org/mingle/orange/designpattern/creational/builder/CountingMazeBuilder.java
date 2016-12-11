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
