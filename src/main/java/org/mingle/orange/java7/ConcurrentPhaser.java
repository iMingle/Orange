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

package org.mingle.orange.java7;

import java.util.List;
import java.util.concurrent.Phaser;

/**
 * A reusable synchronization barrier, similar in functionality to 
 * CyclicBarrier and CountDownLatch but supporting more flexible usage.
 * 
 * @since 1.0
 * @author Mingle
 */
public class ConcurrentPhaser {
    /**
     * A Phaser may be used instead of a CountDownLatch to control 
     * a one-shot action serving a variable number of parties. 
     * The typical idiom is for the method setting this up to first register, 
     * then start the actions, then deregister
     * 
     * @param tasks
     */
    void runTasks(List<Runnable> tasks) {
        final Phaser phaser = new Phaser(1); // "1" to register self
        // create and start threads
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread() {
                public void run() {
                    phaser.arriveAndAwaitAdvance(); // await all creation
                    task.run();
                }
            }.start();
        }

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }

    /**
     * One way to cause a set of threads to repeatedly perform actions 
     * for a given number of iterations is to override onAdvance
     * 
     * @param tasks
     * @param iterations
     */
    void startTasks(List<Runnable> tasks, final int iterations) {
        final Phaser phaser = new Phaser() {
            protected boolean onAdvance(int phase, int registeredParties) {
                return phase >= iterations || registeredParties == 0;
            }
        };
        phaser.register();
        for (final Runnable task : tasks) {
            phaser.register();
            new Thread() {
                public void run() {
                    do {
                        task.run();
                        phaser.arriveAndAwaitAdvance();
                    } while (!phaser.isTerminated());
                }
            }.start();
        }
        phaser.arriveAndDeregister(); // deregister self, don't wait
    }
}
