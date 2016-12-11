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
 * 斐波那契数列算法
 * 
 * @author mingle
 */
public class Fib extends NullFJTask {
    static final int sequentialThreshold = 13; // for tuning
    volatile int number; // argument/result

    Fib(int n) {
        number = n;
    }

    int seqFib(int n) {
        if (n <= 1)
            return n;
        else
            return seqFib(n - 1) + seqFib(n - 2);
    }

    int getAnswer() {
        if (!isDone())
            throw new IllegalStateException("Not yet computed");
        return number;
    }

    @Override
    public void run() {
        int n = number;

        if (n <= sequentialThreshold) // base case
            number = seqFib(n);
        else {
            Fib f1 = new Fib(n - 1); // create subtasks
            Fib f2 = new Fib(n - 2);

            coInvoke(f1, f2); // fork then join both

            number = f1.number + f2.number; // combine results
        }
    }

    public static void main(String[] args) { // sample driver
        try {
            int groupSize = 2; // 2 worker threads
            int num = 35; // compute fib(35)
            FJTaskRunnerGroup group = new FJTaskRunnerGroup(groupSize);
            Fib f = new Fib(num);
            group.invoke(f);
            int result = f.getAnswer();
            System.out.println("Answer: " + result);
        } catch (InterruptedException ex) {} // die
    }
}
