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

package org.orange.java.speciality.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 信号量测试
 *
 * @author mingle
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

    @Override
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}

public class SemaphoreDemo {
    static final int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        final Pool<Object> pool = new Pool<>(Object.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++)
            exec.execute(new CheckoutTask<>(pool));
        System.out.println("All CheckoutTasks created");
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Object f = pool.checkOut();
            System.out.print(i + ": main() thread checked out ");
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
        for (Object f : list)
            pool.checkIn(f);
        for (Object f : list)
            pool.checkIn(f);
        exec.shutdown();
    }
}
