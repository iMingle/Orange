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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 子命令序列
 * 
 * @since 1.0
 * @author Mingle
 */
public class MacroCommand implements Command {
    private List<Command> commands;

    @Override public void execute() {
        Iterator<Command> it = commands.iterator();
        for ( ; it.hasNext(); )
            it.next().execute();
    }

    public void add(Command command) {
        if (commands == null)
            commands = new ArrayList<>();
        commands.add(command);
    }
    
    public void remove(Command command) {
        if (commands == null)
            return;
        commands.remove(command);
    }
}
