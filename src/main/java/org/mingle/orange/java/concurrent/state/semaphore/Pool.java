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

package org.mingle.orange.java.concurrent.state.semaphore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

/**
 * 资源池
 * 
 * @author mingle
 */
public class Pool {
    protected java.util.ArrayList<Object> items = new ArrayList<>();
    protected java.util.HashSet<Object> busy = new HashSet<>();

    protected final Semaphore available;

    public Pool(int n) {
        available = new Semaphore(n);
        initializeItems(n);
    }

    public Object getItem() throws InterruptedException {
        available.acquire();
        return doGet();
    }

    public void returnItem(Object x) {
        if (doReturn(x))
            available.release();
    }

    protected synchronized Object doGet() {
        Object x = items.remove(items.size() - 1);
        busy.add(x); // put in set to check returns
        return x;
    }

    protected synchronized boolean doReturn(Object x) {
        if (busy.remove(x)) {
            items.add(x); // put back into available item list
            return true;
        } else
            return false;
    }

    protected void initializeItems(int n) {
        // Somehow create the resource objects
        // and place them in items list.
    }
}
