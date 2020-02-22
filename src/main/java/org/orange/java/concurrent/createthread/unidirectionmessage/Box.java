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

package org.orange.java.concurrent.createthread.unidirectionmessage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 消息表现,盒子的抽象类
 *
 * @author mingle
 */
public abstract class Box {
    protected Color color = Color.white;

    public synchronized Color getColor() {
        return color;
    }

    public synchronized void setColor(Color c) {
        color = c;
    }

    public abstract java.awt.Dimension size();

    public abstract Box duplicate(); // clone

    public abstract void show(Graphics g, Point origin);// display
}

/**
 * 基本的盒子
 */
class BasicBox extends Box {
    protected Dimension size;

    public BasicBox(int xdim, int ydim) {
        size = new Dimension(xdim, ydim);
    }

    @Override
    public synchronized Dimension size() {
        return size;
    }

    @Override
    public void show(Graphics g, Point origin) {
        g.setColor(getColor());
        g.fillRect(origin.x, origin.y, size.width, size.height);
    }

    @Override
    public synchronized Box duplicate() {
        Box p = new BasicBox(size.width, size.height);
        p.setColor(getColor());
        return p;
    }

}

/**
 * 两个连在一起的盒子
 */
abstract class JoinedPair extends Box {
    protected Box fst; // one of the boxes
    protected Box snd; // the other one

    protected JoinedPair(Box a, Box b) {
        fst = a;
        snd = b;
    }

    public synchronized void flip() { // swap fst/snd
        Box tmp = fst;
        fst = snd;
        snd = tmp;
    }

    public void show(Graphics g, Point p) {
    }

    public Dimension size() {
        return new Dimension(0, 0);
    }

    public Box duplicate() {
        return null;
    }
}

class HorizontallyJoinedPair extends JoinedPair {

    public HorizontallyJoinedPair(Box l, Box r) {
        super(l, r);
    }

    public synchronized Box duplicate() {
        HorizontallyJoinedPair p = new HorizontallyJoinedPair(fst.duplicate(), snd.duplicate());
        p.setColor(getColor());
        return p;
    }

    // ... other implementations of abstract Box methods
}

class VerticallyJoinedPair extends JoinedPair {

    public VerticallyJoinedPair(Box l, Box r) {
        super(l, r);
    }
    // similar
}

/**
 * 边框中的盒子
 */
class WrappedBox extends Box {
    protected Dimension wrapperSize;
    protected Box inner;

    public WrappedBox(Box innerBox, Dimension size) {
        inner = innerBox;
        wrapperSize = size;
    }

    @Override
    public void show(Graphics g, Point p) {
    }

    @Override
    public Dimension size() {
        return new Dimension(0, 0);
    }

    @Override
    public Box duplicate() {
        return null;
    }
}
