/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.flyweight;

import org.mingle.orange.designpattern.structural.bridge.Window;

/**
 * 字符
 * ConcreteFlyweight
 * 
 * @since 1.0
 * @author Mingle
 */
public class Character extends Glyph {
    private final char value;

    public Character(char c) {
        super();
        this.value = c;
    }

    @Override public void draw(Window window, GlyphContext context) {
        super.draw(window, context);
    }

    public char getCh() {
        return value;
    }
    
}
