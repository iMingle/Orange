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

package org.mingle.orange.designpattern.structural.bridge;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 窗口
 *
 * @author mingle
 */
public abstract class Window {
    private final View view;
    private WindowImplementor implementor;

    public Window(View contents) {
        this.view = contents;
    }

    public View getView() {
        return view;
    }

    public WindowImplementor getImplementor() {
        if (implementor == null)
            implementor = WindowSystemFactory.getInstance().makeWindowImplementor();
        return implementor;
    }

    /**
     * requests handled by window
     */
    public abstract void drawContents();

    public void open() {
    }

    public void close() {
    }

    public void iconify() {
    }

    public void deiconify() {
    }

    /**
     * requests forwarded to implementation
     */
    public void setOrigin(Point origin) {
        implementor.setOrigin(origin);
    }

    public void setExtent(Point extent) {
        implementor.setExtent(extent);
    }

    public void raise() {
    }

    public void lower() {
    }

    public void drawLine(Point from, Point to) {
    }

    public void drawRectangle(Point from, Point to) {
        WindowImplementor implementor = getImplementor();
        if (implementor != null)
            implementor.deviceRect(from.getX(), from.getY(), to.getX() - from.getX(), to.getY() - from.getY());
    }

    public void drawPolygon(Point[] points) {
    }

    public void drawText(String text, Point start) {
        implementor.deviceBitmap(text, start.getX(), start.getY());
    }
}
