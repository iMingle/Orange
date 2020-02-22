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

package org.orange.java.speciality;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * Java线程&线程池异常处理
 *
 * @author mingle
 */
public class ThreadHandleException {
    private static final ExecutorService exec = new CustomThreadPoolExecutor(
            10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(Thread.currentThread() + "default uncaught exception handler, exception: " + e);
        });
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        thread.start();

        /** UncaughtExceptionHandler可以处理 */
        exec.execute(() -> {
            throw new RuntimeException();
        });

        /** UncaughtExceptionHandler不能处理,通过future.get()获取到exception */
        Future<?> future = exec.submit(() -> {
            throw new RuntimeException();
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e);
        } catch (ExecutionException e) {
            System.out.println("ExecutionException cause: " + e.getCause());
        }
    }

    private static class CustomThreadPoolExecutor extends ThreadPoolExecutor {
        public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (!Objects.isNull(t))
                System.out.println(Thread.currentThread() + "CustomThreadPoolExecutor afterExecute, exception: " + t);
        }
    }
}
