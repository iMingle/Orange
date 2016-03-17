/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 列表框
 * 
 * @since 1.0
 * @author Mingle
 */
public class ListBox extends Widget {
    @SuppressWarnings("unused") private List<String> items;

    public ListBox(DialogDirector director) {
        super(director);
    }

    public String selection() {
        return "Selected";
    }
    
    public void setList(List<String> items) {
        this.items = items;
    }

    @Override public void handleMouse(MouseEvent event) {
        super.handleMouse(event);
    }
}
