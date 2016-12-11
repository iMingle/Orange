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

package org.mingle.orange.designpattern.creational;

/**
 * 房间
 * 
 * @author mingle
 */
public class Room extends MapSite implements Cloneable {
    @SuppressWarnings("unused")
    private final int no;
    @SuppressWarnings("unused")
    private MapSite[] sides;
    
    public Room(int no) {
        this.no = no;
    }

    @Override
    public void enter() {
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public MapSite getSide(Direction direction) {
        return new Room(0);
    }
    
    public void setSide(Direction direction, MapSite mapSite) {
        
    }
}
