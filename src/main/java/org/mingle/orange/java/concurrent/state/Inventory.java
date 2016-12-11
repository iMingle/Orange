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

package org.mingle.orange.java.concurrent.state;

import java.util.Hashtable;

/**
 * 冲突集合
 * 
 * @author mingle
 */
public class Inventory {
    protected final Hashtable<String, Object> items = new Hashtable<>();
    protected final Hashtable<String, String> suppliers = new Hashtable<>();

    // execution state tracking variables:
    protected int storing = 0; // number of in-progress stores
    protected int retrieving = 0; // number of retrieves

    // ground actions:
    protected void doStore(String description, Object item, String supplier) {
        items.put(description, item);
        suppliers.put(supplier, description);
    }

    protected Object doRetrieve(String description) {
        Object x = items.get(description);
        if (x != null)
            items.remove(description);
        return x;
    }

    public void store(String description, Object item, String supplier)
            throws InterruptedException {
        synchronized (this) { // Before-action
            while (retrieving != 0)
                // don't overlap with retrieves
                wait();
            ++storing; // record exec state
        }

        try {
            doStore(description, item, supplier); // Ground action
        } finally { // After-action
            synchronized (this) { // signal retrieves
                if (--storing == 0) // only necessary when hit zero
                    notifyAll();
            }
        }
    }

    public Object retrieve(String description) throws InterruptedException {
        synchronized (this) { // Before-action
            // wait until no stores or retrieves
            while (storing != 0 || retrieving != 0)
                wait();
            ++retrieving;
        }

        try {
            return doRetrieve(description); // ground action
        } finally {
            synchronized (this) { // After-action
                if (--retrieving == 0)
                    notifyAll();
            }
        }
    }
}
