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

package org.mingle.orange.java.speciality.concurrency;

/**
 * 构造代码高昂的对象
 * 
 * @author mingle
 */
public class Fat {
    @SuppressWarnings("unused")
    private volatile double d;    // 阻止优化
    private static int counter = 0;
    private final int id = counter++;
    
    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double)i;
        }
    }
    
    public void operation() {
        System.out.println(this);
    }
    
    public String toString() {
        return "Fat id: " + id;
    }
}
