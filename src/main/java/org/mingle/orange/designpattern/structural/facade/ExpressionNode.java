/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

import java.util.Iterator;

/**
 * 表达式节点
 * 
 * @since 1.0
 * @author Mingle
 */
public class ExpressionNode extends ProgramNode {

    @Override public void getSourcePosition(Integer line, Integer index) {
        
    }

    @Override public void add(ProgramNode node) {
        
    }

    @Override public void remove(ProgramNode node) {

    }

    @Override public void traverse(CodeGenerator generator) {
        generator.visit(this);
        Iterator<ProgramNode> children = children();
        while (children.hasNext())
            children.next().traverse(generator);
    }

    @Override public Iterator<ProgramNode> children() {
        return null;
    }

}
