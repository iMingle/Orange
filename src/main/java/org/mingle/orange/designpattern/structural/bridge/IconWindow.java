/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.bridge;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class IconWindow extends Window {
    private String bitmapname;

    public IconWindow(View contents) {
        super(contents);
    }

    @Override public void drawContents() {
        WindowImplementor implementor = getImplementor();
        if (implementor != null)
            implementor.deviceBitmap(bitmapname, 0, 0);
    }

}
