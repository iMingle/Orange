/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * 有名变量
 * 
 * @since 1.0
 * @author Mingle
 */
public class VariableExpression implements BooleanExpression {
    private String name;
    
    public VariableExpression(String name) {
        super();
        this.name = name;
    }

    @Override public boolean evaluate(Context context) {
        return context.lookup(name);
    }

    @Override public BooleanExpression replace(String name,
            BooleanExpression expression) {
        if (this.name.equals(name))
            return expression.copy();
        else
            return new VariableExpression(name);
    }

    @Override public BooleanExpression copy() {
        return new VariableExpression(name);
    }

    public String getName() {
        return name;
    }

}
