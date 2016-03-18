/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.templatemethod;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public abstract class View {
    public void display() {
        setFocus();
        doDisplay();
        resetFocut();
    }

    protected abstract void doDisplay();

    private void resetFocut() {
        
    }

    private void setFocus() {
        
    }
}
