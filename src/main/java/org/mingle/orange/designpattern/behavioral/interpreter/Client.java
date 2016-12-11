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

package org.mingle.orange.designpattern.behavioral.interpreter;

/**
 * 
 * 
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
