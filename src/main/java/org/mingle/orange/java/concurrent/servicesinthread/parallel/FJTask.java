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

package org.mingle.orange.java.concurrent.servicesinthread.parallel;

/**
 * fork/join
 * 
 * @author Mingle
 */
public abstract class FJTask implements Runnable {
    abstract boolean isDone();                    // True after task is run
    abstract void cancel();                        // Prematurely set as done
    abstract void fork();                        // Start a dependent task
    abstract void start();                        // Start an arbitrary task
    abstract void yield();                        // Allow another task to run
    abstract void join();                        // Yield caller until done
    abstract void invoke(FJTask t);                // Directly run t
    abstract void coInvoke(FJTask t, FJTask u);    // Fork and join t and u
    abstract void coInvoke(FJTask[] tasks);        // coInvoke all
    abstract void reset();                        // Clear to allow reuse
}
