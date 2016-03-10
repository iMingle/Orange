/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.decorator;

/**
 * 装饰者
 * 
 * @since 1.0
 * @author Mingle
 */
public class Decorator implements VisualComponent {
    private final VisualComponent component;

    public Decorator(VisualComponent component) {
        super();
        this.component = component;
    }

    @Override public void draw() {
        component.draw();
    }

    @Override public void resize() {
        component.resize();
    }

}
