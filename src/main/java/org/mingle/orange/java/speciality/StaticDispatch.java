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

/**
 * 静态分派
 * 
 * @author Mingle
 */
public class StaticDispatch {
    
    static abstract class Human {}
    static class Man extends Human {}
    static class Woman extends Human {}
    
    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);        // hello,guy!
        sr.sayHello(woman);        // hello,guy!
    }

}
