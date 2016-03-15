/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * 对布尔表达式进行操作和求值
 * 
 * @since 1.0
 * @author Mingle
 */
public interface BooleanExpression {
    boolean evaluate(Context context);
    BooleanExpression replace(String name, BooleanExpression expression);
    BooleanExpression copy();
}
