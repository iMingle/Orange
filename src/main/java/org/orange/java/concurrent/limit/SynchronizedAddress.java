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

import java.io.OutputStream;

/**
 * 子类化非同步对象
 * 
 * @author mingle
 */
public class SynchronizedAddress extends Address {
    public synchronized String getStreet() {
        return super.getStreet();
    }

    public synchronized void setStreet(String s) {
        super.setStreet(s);
    }

    public synchronized void printLabel(OutputStream s) {
        super.printLabel(s);
    }
}

class Address {
    protected String street;
    protected String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String s) {
        street = s;
    }

    public void printLabel(OutputStream s) {}
}
