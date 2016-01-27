/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.servicesinthread.parallel;

/**
 * 链接子任务
 * 
 * @since 1.8
 * @author Mingle
 */
public class FibVL extends NullFJTask {
    static final int sequentialThreshold = 13; // for tuning

    volatile int number; // as before
    final FibVL next; // embedded linked list of sibling tasks

    FibVL(int n, FibVL list) {
        number = n;
        next = list;
    }

    int seqFib(int n) {
        if (n <= 1)
            return n;
        else
            return seqFib(n - 1) + seqFib(n - 2);
    }

    public void run() {
        int n = number;
        if (n <= sequentialThreshold)
            number = seqFib(n);
        else {
            FibVL forked = null; // list of subtasks

            forked = new FibVL(n - 1, forked); // prepends to list
            forked.fork();

            forked = new FibVL(n - 2, forked);
            forked.fork();

            number = accumulate(forked);
        }
    }

    // Traverse list, joining each subtask and adding to result
    int accumulate(FibVL list) {
        int r = 0;
        for (FibVL f = list; f != null; f = f.next) {
            f.join();
            r += f.number;
        }
        return r;
    }
}
