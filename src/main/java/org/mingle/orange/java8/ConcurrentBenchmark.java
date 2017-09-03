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

package org.mingle.orange.java8;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * java -jar target/practice-0.0.1-SNAPSHOT.jar org.mingle.orange.java8.ConcurrentBenchmark
 *
 * @author mingle
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConcurrentBenchmark {
    private static final LongAdder adder = new LongAdder();
    private static final AtomicLong atomic = new AtomicLong();

    private void incrementAdder(int value) {
        for (int i = 0; i < value; i++) {
            adder.increment();
        }
    }

    private void incrementAtomic(int value) {
        for (int i = 0; i < value; i++) {
            atomic.incrementAndGet();
        }
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public long measureAdderIncrement1000() {
        incrementAdder(1000);
        return adder.sum();

    }

    @Benchmark
    @OperationsPerInvocation(100000)
    public long measureAdderIncrement100000() {
        incrementAdder(100000);
        return adder.sum();
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public long measureAtomicIncrement1000() {
        incrementAtomic(1000);
        return atomic.get();
    }

    @Benchmark
    @OperationsPerInvocation(100000)
    public long measureAtomicIncrement100000() {
        incrementAtomic(100000);
        return atomic.get();
    }
}
