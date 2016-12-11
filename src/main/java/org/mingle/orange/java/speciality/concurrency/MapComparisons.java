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

package org.mingle.orange.java.speciality.concurrency;

import java.util.Collections;
import java.util.Map;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

import org.mingle.orange.java.util.CountingGenerator;
import org.mingle.orange.java.util.MapData;

/**
 * Map测试
 * 
 * @author Mingle
 */
abstract class MapTest extends Tester<Map<Integer, Integer>> {

    public MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }
    
    class Reader extends TestTask {
        long result = 0;

        @Override
        void test() {
            for (int i = 0; i < testCycles; i++)
                for (int j = 0; j < containerSize; j++)
                    result += testContainer.get(j);
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
        
    }
    
    class Writer extends TestTask {

        @Override
        void test() {
            for (int i = 0; i < testCycles; i++)
                for (int j = 0; j < containerSize; j++)
                    testContainer.put(j, writeData[j]);
        }

        @Override
        void putResults() {
            writeTime = duration;
        }
        
    }
    
    void startReadersAndWriters() {
        for (int i = 0; i < nReaders; i++)
            exec.execute(new Reader());
        for (int i = 0; i < nWriters; i++)
            exec.execute(new Writer());
    }
}

class SynchronizedHashMapTest extends MapTest {

    public SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("Synched HashMap", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> containerInitializer() {
        return Collections.synchronizedMap(
                MapData.map(new CountingGenerator.Integer(), 
                        new CountingGenerator.Integer(), containerSize));
    }
    
}

class ConcurrentHashMapTest extends MapTest {

    public ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.concurrency.Tester#containerInitializer()
     */
    @Override
    Map<Integer, Integer> containerInitializer() {
        return new ConcurrentHashMap<>(
                MapData.map(new CountingGenerator.Integer(), 
                        new CountingGenerator.Integer(), containerSize));
    }
    
}

public class MapComparisons {

    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedHashMapTest(10, 0);
        new SynchronizedHashMapTest(9, 1);
        new SynchronizedHashMapTest(5, 5);
        new ConcurrentHashMapTest(10, 0);
        new ConcurrentHashMapTest(9, 1);
        new ConcurrentHashMapTest(5, 5);
        Tester.exec.shutdown();
    }

}
