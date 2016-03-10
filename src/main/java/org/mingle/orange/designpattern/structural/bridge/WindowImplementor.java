/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.bridge;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 窗口的实现接口
 * 
 * @since 1.0
 * @author Mingle
 */
public interface WindowImplementor {
    void top();
    void botton();
    void setOrigin(Point origin);
    void setExtent(Point extent);
    
    void deviceRect(int x, int y, int width, int height);
    void deviceText(String text, int x, int y);
    void deviceBitmap(String bitmap, int x, int y);
}
