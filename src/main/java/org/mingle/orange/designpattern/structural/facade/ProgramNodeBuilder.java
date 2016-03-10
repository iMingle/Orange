/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 程序节点构建器
 * 
 * @since 1.0
 * @author Mingle
 */
public class ProgramNodeBuilder {
    private ProgramNode node;
    
    public ProgramNode rootNode() {
        return node;
    }
    
    public ProgramNode newVariable(String variableName) {
        return new VariableNode(variableName);
    }
    
    public ProgramNode newAssignment(ProgramNode variable, ProgramNode expression) {
        return new AssignmentNode(variable, expression);
    }
    
    public ProgramNode newReturnStatement(ProgramNode value) {
        return new StatementNode(value);
    }
    
    public ProgramNode newCondition(ProgramNode condition, ProgramNode truePart, ProgramNode falsePart) {
        return new ConditionNode(condition, truePart, falsePart);
    }
}
