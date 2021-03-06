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

import org.orange.java.util.CountingIntegerList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List测试
 * 
 * @author mingle
 */
abstract class ListTest extends Tester<List<Integer>> {

    public ListTest(String testId, int nReaders, int nWriters) {
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
                    testContainer.set(j, writeData[j]);
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

class SynchronizedArrayListTest extends ListTest {

    public SynchronizedArrayListTest(int nReaders, int nWriters) {
        super("Synched ArrayList", nReaders, nWriters);
    }

    @Override
    List<Integer> containerInitializer() {
        return Collections.synchronizedList(new ArrayList<Integer>(new CountingIntegerList(containerSize)));
    }
    
}

class CopyOnWriteArrayListTest extends ListTest {

    public CopyOnWriteArrayListTest(int nReaders, int nWriters) {
        super("CopyOnWriteArrayList", nReaders, nWriters);
    }

    @Override
    List<Integer> containerInitializer() {
        return new CopyOnWriteArrayList<Integer>(new CountingIntegerList(containerSize));
    }
    
}

public class ListComparisons {

    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedArrayListTest(10, 0);
        new SynchronizedArrayListTest(9, 1);
        new SynchronizedArrayListTest(5, 5);
        new CopyOnWriteArrayListTest(10, 0);
        new CopyOnWriteArrayListTest(9, 1);
        new CopyOnWriteArrayListTest(5, 5);
        Tester.exec.shutdown();
    }

}
