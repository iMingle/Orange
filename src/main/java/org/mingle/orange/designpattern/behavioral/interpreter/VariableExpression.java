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
 * 有名变量
 * 
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
