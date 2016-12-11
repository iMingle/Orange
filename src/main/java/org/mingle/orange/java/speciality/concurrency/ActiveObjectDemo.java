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

package org.mingle.orange.java.speciality.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 活动对象测试
 * 
 * @author mingle
 */
public class ActiveObjectDemo {
    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);
    
    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }
    
    public Future<Integer> calculateInt(final int x, final int y) {
        return exec.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("starting " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }
    
    public Future<Float> calculateFloat(final float x, final float y) {
        return exec.submit(new Callable<Float>() {

            @Override
            public Float call() throws Exception {
                System.out.println("starting " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }
    
    public void shutdown() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo d1 = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<>();
        
        for (float f = 0.0f; f < 1.0f; f += 0.2f)
            results.add(d1.calculateFloat(f, f));
        for (int i = 0; i < 5; i++)
            results.add(d1.calculateInt(i, i));
        System.out.println("All asynch calls made");
        while (results.size() > 0) {
            for (Future<?> f : results) {
                if (f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
            }
        }
        d1.shutdown();
    }

}
