/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.*;

/**
 * Comparing the performance of explicit Locks and Atomics versus the
 * synchronized keyword.
 * 
 * @since 1.8
 * @author Mingle
 */
abstract class Accumulator {
    public static long cycles = 50000L;
    // Number of Modifiers and Readers during each test:
    private static final int N = 4;
    public static ExecutorService exec = Executors.newFixedThreadPool(N * 2);
    private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE + 1];
    static {
        // Load the array of random numbers:
        Random rand = new Random(47);
        for (int i = 0; i < SIZE; i++)
            preLoaded[i] = rand.nextInt();
    }

    public abstract void accumulate();

    public abstract long read();

    private class Modifier implements Runnable {
        public void run() {
            for (long i = 0; i < cycles; i++)
                accumulate();
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Reader implements Runnable {
        @SuppressWarnings("unused")
        private volatile long value;

        public void run() {
            for (long i = 0; i < cycles; i++)
                value = read();
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        System.out.printf("%-13s: %13d\n", id, duration);
    }

    public static void report(Accumulator acc1, Accumulator acc2) {
        System.out.printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id,
                (double) acc1.duration / (double) acc2.duration);
    }
}

class BaseLine extends Accumulator {
    {
        id = "BaseLine";
    }

    public void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE)
            index = 0;
    }

    public long read() {
        return value;
    }
}

class SynchronizedTest extends Accumulator {
    {
        id = "synchronized";
    }

    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE)
            index = 0;
    }

    public synchronized long read() {
        return value;
    }
}

class LockTest extends Accumulator {
    {
        id = "Lock";
    }
    private Lock lock = new ReentrantLock();

    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE)
                index = 0;
        } finally {
            lock.unlock();
        }
    }

    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

class AtomicTest extends Accumulator {
    {
        id = "Atomic";
    }
    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);

    public void accumulate() {
        // Oops! Relying on more than one Atomic at
        // a time doesn't work. But it still gives us
        // a performance indicator:
        int i = index.getAndIncrement();
        value.getAndAdd(preLoaded[i]);
        if (++i >= SIZE)
            index.set(0);
    }

    public long read() {
        return value.get();
    }
}

public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synch = new SynchronizedTest();
    static LockTest lock = new LockTest();
    static AtomicTest atomic = new AtomicTest();

    static void test() {
        System.out.println("============================");
        System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synch.timedTest();
        lock.timedTest();
        atomic.timedTest();
        Accumulator.report(synch, baseLine);
        Accumulator.report(lock, baseLine);
        Accumulator.report(atomic, baseLine);
        Accumulator.report(synch, lock);
        Accumulator.report(synch, atomic);
        Accumulator.report(lock, atomic);
    }

    public static void main(String[] args) {
        int iterations = 5; // Default
        if (args.length > 0) // Optionally change iterations
            iterations = new Integer(args[0]);
        // The first time fills the thread pool:
        System.out.println("Warmup");
        baseLine.timedTest();
        // Now the initial test doesn't include the cost
        // of starting the threads for the first time.
        // Produce multiple data points:
        for (int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }
}

/*
Warmup
BaseLine     :      29556822
============================
Cycles       :         50000
BaseLine     :      12757999
synchronized :      63440339
Lock         :      45044554
Atomic       :      12791987
synchronized/BaseLine : 4.97
Lock/BaseLine         : 3.53
Atomic/BaseLine       : 1.00
synchronized/Lock     : 1.41
synchronized/Atomic   : 4.96
Lock/Atomic           : 3.52
============================
Cycles       :        100000
BaseLine     :      30724513
synchronized :     173511951
Lock         :      73043058
Atomic       :      26234863
synchronized/BaseLine : 5.65
Lock/BaseLine         : 2.38
Atomic/BaseLine       : 0.85
synchronized/Lock     : 2.38
synchronized/Atomic   : 6.61
Lock/Atomic           : 2.78
============================
Cycles       :        200000
BaseLine     :      65014952
synchronized :     302570177
Lock         :     143478368
Atomic       :      46986981
synchronized/BaseLine : 4.65
Lock/BaseLine         : 2.21
Atomic/BaseLine       : 0.72
synchronized/Lock     : 2.11
synchronized/Atomic   : 6.44
Lock/Atomic           : 3.05
============================
Cycles       :        400000
BaseLine     :     122586109
synchronized :     676255419
Lock         :     289129162
Atomic       :      88694212
synchronized/BaseLine : 5.52
Lock/BaseLine         : 2.36
Atomic/BaseLine       : 0.72
synchronized/Lock     : 2.34
synchronized/Atomic   : 7.62
Lock/Atomic           : 3.26
============================
Cycles       :        800000
BaseLine     :     242118902
synchronized :    1211061496
Lock         :     587247402
Atomic       :     180928277
synchronized/BaseLine : 5.00
Lock/BaseLine         : 2.43
Atomic/BaseLine       : 0.75
synchronized/Lock     : 2.06
synchronized/Atomic   : 6.69
Lock/Atomic           : 3.25
============================
Cycles       :       1600000
BaseLine     :     470225981
synchronized :    2508036720
Lock         :    1176544316
Atomic       :     349018474
synchronized/BaseLine : 5.33
Lock/BaseLine         : 2.50
Atomic/BaseLine       : 0.74
synchronized/Lock     : 2.13
synchronized/Atomic   : 7.19
Lock/Atomic           : 3.37
============================
Cycles       :       3200000
BaseLine     :     872136469
synchronized :    4998858382
Lock         :    2602939527
Atomic       :     805651661
synchronized/BaseLine : 5.73
Lock/BaseLine         : 2.98
Atomic/BaseLine       : 0.92
synchronized/Lock     : 1.92
synchronized/Atomic   : 6.20
Lock/Atomic           : 3.23

*/