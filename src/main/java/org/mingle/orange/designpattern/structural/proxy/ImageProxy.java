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
 * @author mingle
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
