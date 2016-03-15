/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * not表达式
 * 
 * @since 1.0
 * @author Mingle
 */
public class NotExpression implements BooleanExpression {
    private final BooleanExpression operand;

    public NotExpression(BooleanExpression operand) {
        super();
        this.operand = operand;
    }

    @Override public boolean evaluate(Context context) {
        return !operand.evaluate(context);
    }

    @Override public BooleanExpression replace(String name,
            BooleanExpression expression) {
        return new NotExpression(operand.replace(name, expression));
    }

    @Override public BooleanExpression copy() {
        return new NotExpression(operand.copy());
    }

}
