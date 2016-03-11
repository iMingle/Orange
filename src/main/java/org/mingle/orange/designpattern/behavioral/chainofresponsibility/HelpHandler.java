/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.chainofresponsibility;

/**
 * 图形用户界面的帮助处理器
 * Handler
 * 
 * @since 1.0
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
