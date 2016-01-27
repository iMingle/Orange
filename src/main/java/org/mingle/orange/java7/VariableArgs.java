/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.util.List;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class VariableArgs {
    @SafeVarargs
    public static <T> void addToList(List<T> listArg, T... elements) {
        for (T x : elements) {
            listArg.add(x);
        }
    }
}
