/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
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
