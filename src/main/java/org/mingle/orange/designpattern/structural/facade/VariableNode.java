/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 变量节点
 * 
 * @since 1.0
 * @author Mingle
 */
public class VariableNode extends ProgramNode {
    private final String name;

    public VariableNode(String variableName) {
        this.name = variableName;
    }

    public String getName() {
        return name;
    }

}
