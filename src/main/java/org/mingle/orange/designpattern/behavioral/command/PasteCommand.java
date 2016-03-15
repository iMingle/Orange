/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.command;

/**
 * 粘贴命令
 * 接受者
 * 
 * @since 1.0
 * @author Mingle
 */
public class PasteCommand implements Command {
    private final Document document;

    public PasteCommand(Document document) {
        this.document = document;
    }

    @Override public void execute() {
        document.paste();
    }

}
