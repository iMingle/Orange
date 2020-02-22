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

import org.orange.java.util.BasicGenerator;
import org.orange.java.util.Generator;

import java.util.List;
import java.util.concurrent.*;

/**
 * 在两个任务之间交换对象
 *
 * @author mingle
 */
class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    public ExchangerProducer(Generator<T> generator,
                             Exchanger<List<T>> exchanger, List<T> holder) {
        this.generator = generator;
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < ExchangerDemo.size; i++)
                    holder.add(generator.next());
                // Exchange full for empty
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
            // 可接受的方式退出
        }
    }

}

class ExchangerCustomer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    public ExchangerCustomer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    value = x;
                    holder.remove(x);
                }
            }
        } catch (InterruptedException e) {
            // 可接受的方式退出
        }
        System.out.println("Final value: " + value);
    }

}

public class ExchangerDemo {
    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws InterruptedException {
        if (args.length > 0)
            size = new Integer(args[0]);
        if (args.length > 1)
            delay = new Integer(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Object>> xc = new Exchanger<>();
        List<Object> producerList = new CopyOnWriteArrayList<>(),
                customerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer<>(BasicGenerator.create(Object.class), xc, producerList));
        exec.execute(new ExchangerCustomer<>(xc, customerList));

        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }

}
