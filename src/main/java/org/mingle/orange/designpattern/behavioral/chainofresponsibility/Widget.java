/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.chainofresponsibility;

/**
 * 窗口组件的抽象类
 * ConcreteHandler
 * 
 * @since 1.0
 * @author Mingle
 */
public class Widget extends HelpHandler {
    private Widget parent;

    public Widget(int top) {
        super(top);
    }
    
    public Widget(Widget widget, int top) {
        super(widget, top);
        parent = widget;
    }

    public Widget getParent() {
        return parent;
    }

}
