/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.decorator;

/**
 * 装饰边框
 * 
 * @since 1.0
 * @author Mingle
 */
public class BorderDecorator extends Decorator {
    private final int width;

    public BorderDecorator(VisualComponent component, int borderWidth) {
        super(component);
        this.width = borderWidth;
    }

    @Override public void draw() {
        super.draw();
        drawBorder(width);
    }

    private void drawBorder(int width) {
        
    }
}
