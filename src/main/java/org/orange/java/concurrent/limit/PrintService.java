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

package org.orange.java.concurrent.limit;

/**
 * 组策略-环(多个相互交互的对象一起限制一个资源的情况下)
 * 
 * @author mingle
 */
public class PrintService {
    protected PrintService neighbor = null; // node to take from
    protected Printer printer = null;

    public synchronized void print(byte[] doc) {
        getPrinter().printDocument(doc);
    }

    protected Printer getPrinter() { // PRE: synch lock held
        if (printer == null) // need to take from neighbor
            printer = neighbor.takePrinter();
        return printer;
    }

    synchronized Printer takePrinter() { // called from others
        if (printer != null) {
            Printer p = printer; // implement take protocol
            printer = null;
            return p;
        } else
            return neighbor.takePrinter(); // propagate
    }

    // initialization methods called only during start-up
    synchronized void setNeighbor(PrintService n) {
        neighbor = n;
    }

    synchronized void givePrinter(Printer p) {
        printer = p;
    }

    // Sample code to initialize a ring of new services
    public static void startUpServices(int nServices, Printer p)
            throws IllegalArgumentException {

        if (nServices <= 0 || p == null)
            throw new IllegalArgumentException();

        PrintService first = new PrintService();
        PrintService pred = first;

        for (int i = 1; i < nServices; ++i) {
            PrintService s = new PrintService();
            s.setNeighbor(pred);
            pred = s;
        }

        first.setNeighbor(pred);
        first.givePrinter(p);
    }
}
