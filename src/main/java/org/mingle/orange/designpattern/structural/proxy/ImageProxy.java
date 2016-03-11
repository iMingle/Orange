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
 * 图片代理
 * Proxy
 * 
 * @since 1.0
 * @author Mingle
 */
public class ImageProxy implements Graphic {
    private Image image;
    private Point extent;
    private final String filename;
    
    public ImageProxy(String imageFile) {
        this.filename = imageFile;
        this.extent = Point.ZERO;
    }

    @Override public void draw(Point position) {
        getImage().draw(position);
    }

    @Override public void handleMouse(Event event) {
        getImage().handleMouse(event);
    }

    @Override public Point getExtent() {
        if (Point.ZERO == extent)
            extent = getImage().getExtent();
        return extent;
    }

    @Override public void load(InputStream in) throws IOException {
        byte[] b = new byte[1024];
        in.read(b);
    }

    @Override public void save(OutputStream out) throws IOException {
        out.write(Integer.valueOf(extent.getX()).toString().getBytes());
        out.write(Integer.valueOf(extent.getY()).toString().getBytes());
        out.write(filename.getBytes());
    }

    public Image getImage() {
        if (image == null)
            image = new Image(filename);
        return image;
    }
    
}
