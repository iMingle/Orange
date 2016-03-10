/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

import java.util.Collections;
import java.util.Iterator;

/**
 * 程序节点
 * 
 * @since 1.0
 * @author Mingle
 */
public abstract class ProgramNode {
    public void getSourcePosition(Integer line, Integer index) {
        
    }
    
    public void add(ProgramNode node) {
        
    }
    
    public void remove(ProgramNode node) {
        
    }

    public void traverse(CodeGenerator generator) {
        
    }
    
    @SuppressWarnings("unchecked") public Iterator<ProgramNode> children() {
        return Collections.EMPTY_LIST.iterator();
    }
}
