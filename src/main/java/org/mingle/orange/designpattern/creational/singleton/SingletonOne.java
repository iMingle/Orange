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

package org.mingle.orange.designpattern.creational.singleton;

import java.io.Serializable;


public class SingletonOne implements Serializable {
    private static final long serialVersionUID = 7170169719109367401L;
    
    private static final SingletonOne INSTANCE = new SingletonOne();
    
    private SingletonOne() {
        // 防止借助AccessibleObject.setAccessible的反射机制调用私有构造器
        if (INSTANCE != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }

    public static SingletonOne getInstance() {
        return INSTANCE;
    }
    
    /**
     * 用此方法返回的对象取代反序列化生成的对象,此方法在readObject之后调用,此方法忽略反序列化的对象
     * 注:带有对象引用类型的所有实力域都必须声明为transient的
     * 
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
