/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.command;

/**
 * 简单的不能取消和不需要参数的命令
 * 
 * @since 1.0
 * @author Mingle
 */
public class SimpleCommand implements Command {
    private final Action action;
    private final Receiver receiver;

    public SimpleCommand(Action action, Receiver receiver) {
        this.action = action;
        this.receiver = receiver;
    }

    @Override public void execute() {
        receiver.action(action);
    }

}
