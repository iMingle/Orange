/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.memento;

import java.awt.Point;

/**
 * 图像移动命令
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class MoveCommand {
    private ConstraintSolverMemento state;
    private final Point delta;
    private final Graphic target;
    
    public MoveCommand(Point delta, Graphic target) {
        super();
        this.delta = delta;
        this.target = target;
    }
    
    public void execute() {
        ConstraintSolver solver = ConstraintSolver.getInstance();
        state = solver.createMemento();
        target.move(delta);
        solver.solve();
    }
    
    public void unexecute() {
        ConstraintSolver solver = ConstraintSolver.getInstance();
        target.move(delta);
        solver.setMemento(state);
        solver.solve();
    }
}
