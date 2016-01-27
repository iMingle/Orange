/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 检查中断
 *
 * @since 1.8
 * @author Mingle
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        this.id = ident;
        System.out.println("NeedsCleanup " + id);
    }
    
    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanup n1 = new NeedsCleanup(1);
                
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    
                    try {
                        System.out.println("Calculating");
                        for (int i = 1; i < 2500000; i++) {
                            d += (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
                System.out.println("Exiting via while() test");
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        }
    }
    
}

public class InterruptingIdiom {

    public static void main(String[] args) throws Exception, InterruptedException {
        if (args.length != 1) {
            System.out.println("Usage: java InterruptingIdiom delay-in-mS");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }

}
