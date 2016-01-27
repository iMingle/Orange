/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 信号量测试
 * 
 * @since 1.8
 * @author Mingle
 */
class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;
    
    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }


    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + "checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checked in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            // 可接受的方式退出
        }
    }
    
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}

public class SemaphoreDemo {
    static final int SIZE = 25;
    
    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++)
            exec.execute(new CheckoutTask<Fat>(pool));
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<Fat>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.print(i + ": main() thread checked out ");
            f.operation();
            list.add(f);
        }
        
        Future<?> blocked = exec.submit(new Runnable() {
            
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("checkOut() interrupted");
                }
            }
        });
        
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);    // 打断blocked的调用
        System.out.println("Checking in objects in " + list);
        for (Fat f : list)
            pool.checkIn(f);
        for (Fat f : list)
            pool.checkIn(f);
        exec.shutdown();
    }
}
