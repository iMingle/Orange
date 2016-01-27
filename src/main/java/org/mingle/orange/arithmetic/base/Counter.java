package org.mingle.orange.arithmetic.base;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;


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
