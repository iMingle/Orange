/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 条件表达式节点
 * 
 * @since 1.0
 * @author Mingle
 */
public class ConditionNode extends ProgramNode {
    private final ProgramNode condition;
    private final ProgramNode truePart;
    private final ProgramNode falsePart;;
    
    public ConditionNode(ProgramNode condition, ProgramNode truePart,
            ProgramNode falsePart) {
        this.condition = condition;
        this.truePart = truePart;
        this.falsePart = falsePart;
    }

    public ProgramNode getCondition() {
        return condition;
    }

    public ProgramNode getTruePart() {
        return truePart;
    }

    public ProgramNode getFalsePart() {
        return falsePart;
    }

}
