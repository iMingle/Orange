/*
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
 * limitations under the License.
 */

package org.mingle.orange.designpattern.behavioral.command;

/**
 * 打开命令
 * 
 * @author mingle
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
