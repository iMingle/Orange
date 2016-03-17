/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.mediator;

/**
 * 负责对话框管理
 * 中介者
 * 
 * @since 1.0
 * @author Mingle
 */
public abstract class DialogDirector {
    public void showDialog() {}
    
    public void closeDialog() {}
    
    public abstract void changeWidget(Widget widget);
    
    protected abstract void createWidgets();
}
