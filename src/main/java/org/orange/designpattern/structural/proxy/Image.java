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

package org.orange.designpattern.structural.proxy;

import org.orange.designpattern.structural.Point;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片
 * RealSubject
 *
 * @author mingle
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
