/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.event.MouseEvent;

/**
 * 输入域
 * 
 * @since 1.0
 * @author Mingle
 */
public class EntryField extends Widget {
    private String text;

    public EntryField(DialogDirector director) {
        super(director);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override public void handleMouse(MouseEvent event) {
        super.handleMouse(event);
    }
}
