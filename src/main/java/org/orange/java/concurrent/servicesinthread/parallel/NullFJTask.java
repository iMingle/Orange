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

package org.orange.java.concurrent.servicesinthread.parallel;

/**
 * FJTask的空实现
 * 
 * @author mingle
 */
public class NullFJTask extends FJTask {

    @Override
    public void run() {
        
    }

    @Override
    boolean isDone() {
        return false;
    }

    @Override
    void cancel() {
        
    }

    @Override
    void fork() {
        
    }

    @Override
    void start() {
        
    }

    @Override
    void yield() {
        
    }

    @Override
    void join() {
        
    }

    @Override
    void invoke(FJTask t) {
        
    }

    @Override
    void coInvoke(FJTask t, FJTask u) {
        
    }

    @Override
    void coInvoke(FJTask[] tasks) {
        
    }

    @Override
    void reset() {
        
    }

}
