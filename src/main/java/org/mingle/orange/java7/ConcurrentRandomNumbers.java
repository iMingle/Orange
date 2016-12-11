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

import java.util.concurrent.ThreadLocalRandom;

/**
 * In JDK 7, java.util.concurrent includes a convenience class, 
 * ThreadLocalRandom, for applications that expect to use random 
 * numbers from multiple threads or ForkJoinTasks.
 * For concurrent access, using ThreadLocalRandom instead of Math.random() 
 * results in less contention and, ultimately, better performance.
 * 
 * @author mingle
 */
public class ConcurrentRandomNumbers {
    public static void main(String[] args) {
        int rand = ThreadLocalRandom.current().nextInt(1, 100);
        System.out.println(rand);
    }
}
