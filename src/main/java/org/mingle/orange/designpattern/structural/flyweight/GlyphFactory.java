/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.flyweight;

/**
 * 图形工厂,负责创建Glyph并确保对它们进行合理共享.
 * 
 * @since 1.0
 * @author Mingle
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
