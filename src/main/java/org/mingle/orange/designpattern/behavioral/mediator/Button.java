/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.event.MouseEvent;

/**
 * 按键
 * 
 * @since 1.0
 * @author Mingle
 */
public class Button extends Widget {

    public Button(DialogDirector director) {
        super(director);
    }

    public void setText(String text) {
        
    }
    
    @Override public void handleMouse(MouseEvent event) {
        changed();
    }
}
