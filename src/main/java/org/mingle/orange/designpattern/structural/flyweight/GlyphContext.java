/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.flyweight;

import java.awt.Font;

/**
 * 图形上下文,用于存储外部状态(字体)
 * 
 * @since 1.0
 * @author Mingle
 */
public class GlyphContext {
    @SuppressWarnings("unused") private int index;
    @SuppressWarnings("unused") private BTree<Glyph, Font> fonts;

    public Font font() {
        return null;
    }
    
    public void setFont(Font font, int span) {
        
    }

    public Glyph current() {
        return null;
    }
    
    public void next(int step) {
        index++;
    }
    
    public void insert(int quantity) {
        
    }

}
