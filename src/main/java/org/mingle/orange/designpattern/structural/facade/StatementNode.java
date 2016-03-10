/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 语句节点
 * 
 * @since 1.0
 * @author Mingle
 */
public class StatementNode extends ProgramNode {
    private ProgramNode value;

    public StatementNode(ProgramNode value) {
        this.value = value;
    }

    public ProgramNode getValue() {
        return value;
    }

}
