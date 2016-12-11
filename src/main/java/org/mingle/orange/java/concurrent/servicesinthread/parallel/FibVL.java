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

package org.mingle.orange.java.concurrent.servicesinthread.parallel;

/**
 * 链接子任务
 * 
 * @author mingle
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
