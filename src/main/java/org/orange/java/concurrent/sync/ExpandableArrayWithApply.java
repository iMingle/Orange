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

package org.orange.java.concurrent.sync;

/**
 * 遍历-同步聚合操作
 * 
 * @author mingle
 */
public class ExpandableArrayWithApply extends ExpandableArray {

    public ExpandableArrayWithApply(int cap) {
        super(cap);
    }

    /**
     * 拥有集合的锁的时间很长
     * 
     * @param p
     */
    public synchronized void applyToAll(Procedure p) {
        for (int i = 0; i < size; ++i)
            p.apply(data[i]);
    }
    
    public static void main(String[] args) {
        ExpandableArrayWithApply v = new ExpandableArrayWithApply(5);
        v.add("One");
        v.add("Two");
        v.applyToAll(new Procedure() {
            
            @Override
            public void apply(Object obj) {
                System.out.println(obj);
            }
        });
    }
}
