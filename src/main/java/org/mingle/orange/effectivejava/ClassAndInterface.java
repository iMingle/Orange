/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类和接口
 * 
 * @since 1.8
 * @author Mingle
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
