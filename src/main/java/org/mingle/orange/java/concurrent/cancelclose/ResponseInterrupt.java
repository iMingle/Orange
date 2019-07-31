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

package org.mingle.orange.java.concurrent.cancelclose;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 响应中断
 *
 * @author mingle
 */
public class ResponseInterrupt {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(2);
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    /**
     * 在外部线程中安排中断(不要这么做)
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun1(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Thread taskThread = Thread.currentThread();
        cancelExec.schedule(() -> {
            taskThread.interrupt();
        }, timeout, unit);
        r.run();
    }

    /**
     * 在专门的线程中中断任务
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun2(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null)
                    throw new RuntimeException(t);
            }
        }

        RethrowableTask task = new RethrowableTask();
        Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(() -> {
            taskThread.interrupt();
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));    // 无法确定是因为线程正常退出还是超时退出
        task.rethrow();
    }

    /**
     * 通过Future来取消任务
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (ExecutionException e) {
            // 重新抛出异常
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            // 超时取消任务
        } finally {
            task.cancel(true);    // 如果任务正在运行,那么将被中断
        }
    }
}
