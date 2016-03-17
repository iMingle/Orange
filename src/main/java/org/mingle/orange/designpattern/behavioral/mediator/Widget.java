/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.event.MouseEvent;

/**
 * 窗口小组件
 * 同事类
 * 
 * @since 1.0
 * @author Mingle
 */
public class Widget {
    private final DialogDirector director;

    public Widget(DialogDirector director) {
        super();
        this.director = director;
    }
    
    public void changed() {
        director.changeWidget(this);
    }
    
    public void handleMouse(MouseEvent event) {
        
    }
}
