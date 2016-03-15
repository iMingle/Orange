/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
