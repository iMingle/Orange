/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 机器代码生成器
 * 
 * @since 1.0
 * @author Mingle
 */
public class CodeGenerator {
    private final BytecodeStream output;

    public CodeGenerator(BytecodeStream output) {
        this.output = output;
    }
    
    public void visit(StatementNode statementNode) {
        
    }
    
    public void visit(ExpressionNode expressionNode) {
        
    }

    public BytecodeStream getOutput() {
        return output;
    }
}
