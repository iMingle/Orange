/*
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
 * limitations under the License.
 */

package org.mingle.orange.designpattern.structural.flyweight;

/**
 * 图形工厂,负责创建Glyph并确保对它们进行合理共享.
 * 
 * @author mingle
 */
public class GlyphFactory {
    private static final int NCHARCODES = 128;
    private Character[] cache = new Character[NCHARCODES];
    
    public GlyphFactory() {
        for (int i = 0; i < cache.length; i++)
            cache[i] = new Character((char)i);
    }
    
    /**
     * 获取共享数据
     * 
     * @param c
     * @return
     */
    public Character createCharacter(char c) {
        if(c <= 127)
            return cache[c];
        return new Character(c);
    }
    
    public Row createRow() {
        return new Row();
    }
    
    public Column createColumn() {
        return new Column();
    }
}
