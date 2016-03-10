/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 赋值语句节点
 * 
 * @since 1.0
 * @author Mingle
 */
public class AssignmentNode extends ProgramNode {
    private final ProgramNode variable;
    private final ProgramNode expression;

    public AssignmentNode(ProgramNode variable, ProgramNode expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public ProgramNode getVariable() {
        return variable;
    }

    public ProgramNode getExpression() {
        return expression;
    }

}
