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

package org.mingle.orange.designpattern.structural.facade;

import java.util.Iterator;

/**
 * 表达式节点
 * 
 * @author mingle
 */
public class ExpressionNode extends ProgramNode {

    @Override public void getSourcePosition(Integer line, Integer index) {
        
    }

    @Override public void add(ProgramNode node) {
        
    }

    @Override public void remove(ProgramNode node) {

    }

    @Override public void traverse(CodeGenerator generator) {
        generator.visit(this);
        Iterator<ProgramNode> children = children();
        while (children.hasNext())
            children.next().traverse(generator);
    }

    @Override public Iterator<ProgramNode> children() {
        return null;
    }

}
