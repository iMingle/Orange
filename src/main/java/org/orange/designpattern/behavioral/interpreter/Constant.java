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
 * 常量
 *
 * @author mingle
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
