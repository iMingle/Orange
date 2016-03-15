/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class Client {

    public static void main(String[] args) {
        BooleanExpression expression;
        Context context = new Context();
        
        VariableExpression x = new VariableExpression("X");
        VariableExpression y = new VariableExpression("Y");
        
        expression = new OrExpression(new AndExpression(new Constant(true), x), new AndExpression(y, new NotExpression(x)));
        context.assign(x, false);
        context.assign(y, true);
        
        System.out.println(expression.evaluate(context));
        
        VariableExpression z = new VariableExpression("Z");
        
        BooleanExpression replacement = expression.replace("Y", new NotExpression(z));
        context.assign(z, true);
        
        System.out.println(replacement.evaluate(context));
    }

}
