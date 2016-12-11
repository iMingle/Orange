/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.java.speciality.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 同步控制块
 *
 * @since 1.0
 * @author Mingle
 */
class Pair {    // 非线程安全
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void incrementX() {
        x++;
    }
    
    public void incrementY() {
        y++;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
    
    public void checkState() {
        if (x != y)
            throw new PairValuesNotEqualException();
    }
    
    public class PairValuesNotEqualException extends RuntimeException {
        private static final long serialVersionUID = 2649020871423333411L;

        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }
}

// 在线程安全的类中保护Pair
abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
    
    public synchronized Pair getPair() {
        return new Pair(p.getX(), p.getY());
    }
    
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ignore) {}
    }
    
    public abstract void increment();
}

class PairManager1 extends PairManager {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.concurrency.PairManager#increment()
     */
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
    
}

class PairManager2 extends PairManager {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.concurrency.PairManager#increment()
     */
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
    
}

class PairManipulator implements Runnable {
    private PairManager pm;
    
    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (true)
            pm.increment();
    }
    
    public String toString() {
        return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private PairManager pm;
    
    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
    
}

public class CriticalSection {
    static void testApproaches(PairManager pman1, PairManager pman2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator pm1 = new PairManipulator(pman1),
                pm2 = new PairManipulator(pman2);
        PairChecker pcheck1 = new PairChecker(pman1),
                pcheck2 = new PairChecker(pman2);
        
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pcheck1);
        exec.execute(pcheck2);
        
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pman1 = new PairManager1(),
        pman2 = new PairManager2();
        testApproaches(pman1, pman2);
    }

}
