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

package org.mingle.orange.designpattern.behavioral.memento;

/**
 * 连接约束
 * 原发器
 * 
 * @author mingle
 */
public class ConstraintSolver {
    private static ConstraintSolver solver;
    
    private ConstraintSolver() {
        if (solver != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }
    
    public static synchronized ConstraintSolver getInstance() {
        if (solver == null)
            solver = new ConstraintSolver();
        return solver;
    }
    
    /**
     * 解释约束
     */
    public void solve() {
        
    }
    
    public void addConstraint(Graphic startConnection, Graphic endConnection) {
        
    }
    
    public void removeConstraint(Graphic startConnection, Graphic endConnection) {
        
    }
    
    public ConstraintSolverMemento createMemento() {
        return new ConstraintSolverMemento();
    }
    
    public void setMemento(ConstraintSolverMemento memento) {
        
    }
}
