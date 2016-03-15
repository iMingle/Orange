/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.command;

/**
 * 打开命令
 * 
 * @since 1.0
 * @author Mingle
 */
public class OpenCommand implements Command {
    private final Application application;
    
    public OpenCommand(Application application) {
        this.application = application;
    }

    @Override public void execute() {
        final String name = askUser();
        if (name != null) {
            Document document = new Document(name);
            application.add(document);
            document.open();
        }
    }

    /**
     * 提示用户输入要打开的文档名
     * 
     * @return
     */
    private String askUser() {
        return "document.txt";
    }
}
