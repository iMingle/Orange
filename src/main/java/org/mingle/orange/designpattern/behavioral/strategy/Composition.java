/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.strategy;

import java.awt.Component;

/**
 * 文档(包含正文和图形)
 * 
 * @since 1.0
 * @author Mingle
 */
public class Composition {
    @SuppressWarnings("unused") private final Compositor compositor;
    @SuppressWarnings("unused") private Component components; // the list of components
    @SuppressWarnings("unused") private int componentCount; // the number of components
    @SuppressWarnings("unused") private int lineWidth; // the Composition's line width
    @SuppressWarnings("unused") private int lineBreaks; // the position of linebreaks in components
    @SuppressWarnings("unused") private int lineCount; // the number of lines
    
    public Composition(Compositor compositor) {
        super();
        this.compositor = compositor;
    }
    
    public void repair() {
        
    }
}
