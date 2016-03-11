/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.flyweight;

import java.awt.Font;

import org.mingle.orange.designpattern.structural.bridge.Window;

/**
 * 图形基类
 * Flyweight
 * 
 * @since 1.0
 * @author Mingle
 */
public abstract class Glyph {
    public void draw(Window window, GlyphContext context) {
        
    }
    
    public void setFont(Font font, GlyphContext context) {
        
    }
    
    public Font getFont(GlyphContext context) {
        return context.font();
    }
    
    public void first(GlyphContext context) {
        
    }
    
    public void next(GlyphContext context) {
        
    }
    
    public boolean isDone(GlyphContext context) {
        return true;
    }
    
    public Glyph current(GlyphContext context) {
        return context.current();
    }
    
    public void insert(Glyph glyph, GlyphContext context) {
        
    }
    
    public void remove(GlyphContext context) {
        
    }
}
