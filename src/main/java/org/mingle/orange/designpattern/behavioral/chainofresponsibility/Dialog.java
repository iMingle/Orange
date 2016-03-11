/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.chainofresponsibility;

/**
 * 对话框
 * ConcreteHandler
 * 
 * @since 1.0
 * @author Mingle
 */
public class Dialog extends Widget {

    public Dialog(HelpHandler handler, int top) {
        super(top);
        setHandler(handler, top);
    }

    @Override public void handleHelp() {
        if (hasHelp())
            System.out.println("Dialog Help");
        else
            super.handleHelp();
    }

}
