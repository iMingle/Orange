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

package org.mingle.orange.util.mvel;

import lombok.Data;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

/**
 * @author mingle
 */
public class MvelTest {

    public static void main(String[] args) {
        String expression = "((name contains 'Mingle') && (age >= 25))";
//        MvelDomainInner domainInner = new MvelDomainInner("JinMinglei", 25);            // error
        MvelDomain domain = new MvelDomain("JinMinglei", 25);

        Object compiledExpression = MVEL.compileExpression(expression, new ParserContext());
        System.out.println(MVEL.executeExpression(compiledExpression, domain, Boolean.class));
    }
}

/**
 * 非public类
 */
@Data
class MvelDomainInner {
    private String name;
    private int age;

    public MvelDomainInner() {
    }

    public MvelDomainInner(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
}
