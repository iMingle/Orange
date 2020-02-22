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

package org.orange.designpattern.creational.singleton;

/**
 * 单例迷宫工厂
 *
 * @author mingle
 */
public class MazeFactory {
    private volatile static MazeFactory instance;

    private MazeFactory() {
        // 防止借助AccessibleObject.setAccessible的反射机制调用私有构造器
        if (instance != null)
            throw new IllegalStateException("singleton, cannot create another object");
    }

    public static MazeFactory getInstance() {
        if (instance == null) {
            synchronized (MazeFactory.class) {
                if (instance == null)
                    instance = new MazeFactory();
            }
        }
        return instance;
    }
}
