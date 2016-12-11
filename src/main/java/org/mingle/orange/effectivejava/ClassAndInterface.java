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

package org.mingle.orange.effectivejava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类和接口
 * 
 * @author mingle
 */
public class ClassAndInterface {
    // 基本数据类型或不可变类
    private static final Integer[] PRIVATE_VALUES = {Integer.valueOf(0), Integer.valueOf(1)
    };
    public static final List<Integer> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
    
    public static final Integer[] values() {
        return PRIVATE_VALUES.clone();
    }
    
}
