/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.bridge;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 窗口
 * 
 * @since 1.0
 * @author Mingle
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
    
    public void open() {}
    
    public void close() {}
    
    public void iconify() {}
    
    public void deiconify() {}
    
    /**
     * requests forwarded to implementation
     */
    public void setOrigin(Point origin) {
        implementor.setOrigin(origin);
    }
    
    public void setExtent(Point extent) {
        implementor.setExtent(extent);
    }
    
    public void raise() {}
    
    public void lower() {}
    
    public void drawLine(Point from, Point to) {}
    
    public void drawRectangle(Point from, Point to) {
        WindowImplementor implementor = getImplementor();
        if (implementor != null)
            implementor.deviceRect(from.getX(), from.getY(), to.getX() - from.getX(), to.getY() - from.getY());
    }
    
    public void drawPolygon(Point[] points) {}
    
    public void drawText(String text, Point start) {
        implementor.deviceBitmap(text, start.getX(), start.getY());
    }
}
