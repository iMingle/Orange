/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 用内部类创建线程
 *
 * @since 1.0
 * @author Mingle
 */
class InnerThread1 {
    private int countDown = 5;
    @SuppressWarnings("unused")
    private Inner inner;
    
    public InnerThread1(String name) {
        inner = new Inner(name);
    }
    
    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }
        
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0)
                        return;
                    sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }
        
        public String toString() {
            return getName() + ": " + countDown;
        }
    }
}

class InnerThread2 {
    private int countDown = 5;
    private Thread t;
    
    public InnerThread2(String name) {
        t = new Thread(name) {
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0)
                            return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }
            
            public String toString() {
                return getName() + ": " + countDown;
            }
        };
        t.start();
    }
}

class InnerRunnable1 {
    private int countDown = 5;
    @SuppressWarnings("unused")
    private Inner inner;
    
    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
    
    private class Inner implements Runnable {
        Thread t;
        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }
        
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0)
                        return;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }
        
        public String toString() {
            return t.getName() + ": " + countDown;
        }
    }
}

class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;
    
    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0)
                            return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }
            
            public String toString() {
                return Thread.currentThread().getName() + ": " + countDown;
            }
        }, name);
        t.start();
    }
}

class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;
    
    public ThreadMethod(String name) {
        this.name = name;
    }
    
    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (--countDown == 0)
                                return;
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("sleep() interrupted");
                    }
                }
                
                public String toString() {
                    return t.getName() + ": " + countDown;
                }
            };
            t.start();
        }
    }
}

public class ThreadVariations {

    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }

}
