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

package org.mingle.orange.designpattern.structural.facade;

/**
 * 程序节点构建器
 * 
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
