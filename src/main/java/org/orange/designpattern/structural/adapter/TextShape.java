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

package org.orange.designpattern.structural.adapter;

import org.orange.designpattern.structural.Point;

/**
 * @author mingle
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
