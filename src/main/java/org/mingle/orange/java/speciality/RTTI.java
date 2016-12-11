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

package org.mingle.orange.java.speciality;

class Useful {
    public void f() {
    }

    public void g() {
    }
}

class MoreUseful extends Useful {
    public void f() {
    }

    public void g() {
    }

    public void u() {
    }

    public void v() {
    }

    public void w() {
    }
}

/**
 * Run-Time Type Information 运行时类型识别
 * 
 * @author mingle
 */
public class RTTI {
    public static void main(String[] args) {
        Useful[] x = { new Useful(), new MoreUseful() };
        x[0].f();
        x[1].g();
        // Compile time: method not found in Useful:
        // ! x[1].u();
        ((MoreUseful) x[1]).u(); // Downcast/RTTI
        ((MoreUseful) x[0]).u(); // Exception thrown    java.lang.ClassCastException
    }
}
