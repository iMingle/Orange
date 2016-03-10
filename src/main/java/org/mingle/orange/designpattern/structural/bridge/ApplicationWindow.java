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
public class ApplicationWindow extends Window {

    public ApplicationWindow(View contents) {
        super(contents);
    }

    @Override public void drawContents() {
        getView().drawOn(this);
    }

}
