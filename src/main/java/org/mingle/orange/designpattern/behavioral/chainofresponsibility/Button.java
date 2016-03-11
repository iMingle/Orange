/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.chainofresponsibility;

/**
 * 按钮
 * ConcreteHandler
 * 
 * @since 1.0
 * @author Mingle
 */
public class Button extends Widget {

    public Button(Widget widget, int top) {
        super(widget, top);
    }

    @Override public void handleHelp() {
        if (hasHelp())
            System.out.println("Button Help");
        else
            super.handleHelp();
    }

}
