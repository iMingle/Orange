/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.chainofresponsibility;

/**
 * 应用
 * ConcreteHandler
 * 
 * @since 1.0
 * @author Mingle
 */
public class Application extends HelpHandler {

    public Application(int top) {
        super(null, top);
    }

    @Override public void handleHelp() {
        System.out.println("Application Help");
    }

    public static void main(String[] args) {
        Application application = new Application(3);
        Dialog dialog = new Dialog(application, 1);
        Button button = new Button(dialog, 2);
        button.handleHelp();
    }
}
