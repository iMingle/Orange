/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class FJTaskRunnerGroup {
    private final ExecutorService exec;

    public FJTaskRunnerGroup(int groupSize) {
        exec = Executors.newFixedThreadPool(groupSize);
    }

    public void invoke(Fib f) throws InterruptedException {
        exec.execute(f);
    }

    public void execute(NQueens nQueens) {
        exec.execute(nQueens);
    }

}
