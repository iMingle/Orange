/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.proxy;

import java.awt.Event;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 图形对象
 * Subject
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Graphic {
    void draw(Point position);
    void handleMouse(Event event);
    Point getExtent();
    void load(InputStream in) throws IOException;
    void save(OutputStream out) throws IOException;
}
