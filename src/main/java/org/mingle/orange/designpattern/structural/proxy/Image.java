/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.proxy;

import java.awt.Event;
import java.io.InputStream;
import java.io.OutputStream;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 图片
 * RealSubject
 * 
 * @since 1.0
 * @author Mingle
 */
public class Image implements Graphic {
    private final String filepath;

    public Image(String file) {
        this.filepath = file;
    }

    @Override public void draw(Point position) {
        
    }

    @Override public void handleMouse(Event event) {
        
    }

    @Override public Point getExtent() {
        return null;
    }

    @Override public void load(InputStream in) {
        
    }

    @Override public void save(OutputStream out) {
        
    }

    public String getFilepath() {
        return filepath;
    }

}
