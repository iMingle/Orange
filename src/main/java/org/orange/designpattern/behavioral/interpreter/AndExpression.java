/*
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
 * limitations under the License.
 */

package org.orange.designpattern.behavioral.interpreter;

/**
 * and表达式
 *
 * @author mingle
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
