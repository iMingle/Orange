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

package org.mingle.orange.designpattern.structural.flyweight;

import java.awt.Font;

import org.mingle.orange.designpattern.structural.bridge.Window;

/**
 * 图形基类
 * Flyweight
 * 
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
