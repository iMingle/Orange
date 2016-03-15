/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.adapter;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class TextShape implements Shape, TextView {
    private final TextView view;

    public TextShape(TextView view) {
        super();
        this.view = view;
    }

    @Override public void origin(Integer x, Integer y) {
        view.origin(x, y);
    }

    @Override public void extent(Integer width, Integer height) {
        view.extent(width, height);
    }

    @Override public boolean isEmpty() {
        return view.isEmpty();
    }

    @Override public void scope(Point bottomLeft, Point topRight) {
        Integer bottom = 0, left = 0, width = 0, height = 0;
        origin(bottom, left);
        extent(width, height);
        bottomLeft = new Point(bottom, left);
        topRight = new Point(width, height);
    }

    @Override
    public Manipulator createManipulator() {
        return new TextManipulator(this);
    }

}