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

package org.mingle.orange.designpattern.behavioral.chainofresponsibility;

/**
 * 图形用户界面的帮助处理器
 * Handler
 * 
 * @author Mingle
 */
public class HelpHandler {
    public static final int NO_HELP_TOPIC = -1;
    private HelpHandler successor;
    private int top = NO_HELP_TOPIC;
    
    public HelpHandler(int top) {
        this.top = top;
    }
    
    public HelpHandler(HelpHandler successor, int top) {
        this.successor = successor;
        this.top = top;
    }
    
    public boolean hasHelp() {
        return top != NO_HELP_TOPIC;
    }
    
    public void setHandler(HelpHandler successor, int top) {
        this.successor = successor;
        this.top = top;
    }
    
    public void handleHelp() {
        if (successor != null)
            successor.handleHelp();
    }
}
