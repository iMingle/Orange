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

package org.mingle.orange.java.generic;

/**
 * generic class pair
 * 
 * @since 1.0
 * @author Mingle
 */
public class Pair<T> {
    private T first;
    private T second;
    
    /**
     * 
     */
    public Pair() {
    }
    
    /**
     * @param first
     * @param second
     */
    public Pair(T first, T second) {
        super();
        this.first = first;
        this.second = second;
    }
    /**
     * @return the first
     */
    public T getFirst() {
        return first;
    }
    /**
     * @param first the first to set
     */
    public void setFirst(T first) {
        this.first = first;
    }
    /**
     * @return the second
     */
    public T getSecond() {
        return second;
    }
    /**
     * @param second the second to set
     */
    public void setSecond(T second) {
        System.out.println("Pair setSecond");
        this.second = second;
    }
}
