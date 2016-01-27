/**
 * Copyright (c) 2016, Mingle. All rights reserved.
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
 * @since 1.8
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
