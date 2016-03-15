/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * and表达式
 * 
 * @since 1.0
 * @author Mingle
 */
public class AndExpression implements BooleanExpression {
    private final BooleanExpression operand1;
    private final BooleanExpression operand2;

    public AndExpression(BooleanExpression operand1, BooleanExpression operand2) {
        super();
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override public boolean evaluate(Context context) {
        return operand1.evaluate(context) && operand2.evaluate(context);
    }

    @Override public BooleanExpression replace(String name,
            BooleanExpression expression) {
        return new AndExpression(operand1.replace(name, expression), operand2.replace(name, expression));
    }

    @Override public BooleanExpression copy() {
        return new AndExpression(operand1.copy(), operand2.copy());
    }

}
