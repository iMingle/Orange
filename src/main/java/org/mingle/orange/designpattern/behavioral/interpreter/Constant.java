/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * 常量
 * 
 * @since 1.0
 * @author Mingle
 */
public class Constant implements BooleanExpression {
    private final boolean value;

    public Constant(boolean value) {
        super();
        this.value = value;
    }

    @Override public boolean evaluate(Context context) {
        return value;
    }

    @Override public BooleanExpression replace(String name,
            BooleanExpression expression) {
        return new Constant(value);
    }

    @Override public BooleanExpression copy() {
        return new Constant(value);
    }

}
