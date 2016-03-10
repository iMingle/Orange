/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.bridge;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 生成X窗口
 * 
 * @since 1.0
 * @author Mingle
 */
public class XWindowImplementor implements WindowImplementor {

    @Override public void top() {}

    @Override public void botton() {}

    @Override public void setOrigin(Point origin) {}

    @Override public void setExtent(Point extent) {}

    @Override public void deviceRect(int x, int y, int width, int height) {}

    @Override public void deviceText(String text, int x, int y) {}

    @Override public void deviceBitmap(String bitmap, int x, int y) {}

}
