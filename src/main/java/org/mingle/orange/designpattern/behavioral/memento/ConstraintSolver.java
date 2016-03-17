/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.memento;

/**
 * 连接约束
 * 原发器
 * 
 * @since 1.0
 * @author Mingle
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
