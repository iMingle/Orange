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

package org.orange.arithmetic.base;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Counter {
    private int count;
    private final String name;
    
    public Counter(String id) {
        this.name = id;
    }
    
    public void increment() {
        count++;
    }
    
    public int tally() {
        return count;
    }

    @Override
    public String toString() {
        return count + " " + name;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        
        for (int i = 0; i < 100000; i++) {
            if (StdRandom.bernoulli(0.5)) {
                heads.increment();
            } else {
                tails.increment();
            }
        }
        //shenme
        StdOut.println(heads);
        StdOut.println(tails);
        
        int d = heads.tally() - tails.tally();
        
        StdOut.println("delta: " + Math.abs(d));
    }

}