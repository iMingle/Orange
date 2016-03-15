/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文,定义变量到布尔值的映射
 * 
 * @since 1.0
 * @author Mingle
 */
public class Context {
    public final Map<String, Boolean> values = new HashMap<>();
    
    public boolean lookup(String name) {
        return values.get(name);
    }
    
    public void assign(VariableExpression expression, boolean value) {
        values.put(expression.getName(), value);
    }
}
