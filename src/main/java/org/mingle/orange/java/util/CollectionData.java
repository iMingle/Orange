/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

import java.util.*;

/**
 * 集合容器
 * 
 * @since 1.8
 * @author Mingle
 */
public class CollectionData<T> extends ArrayList<T> {
    private static final long serialVersionUID = 520586597408683553L;

    public CollectionData(Generator<T> gen, int quantity) {
        for (int i = 0; i < quantity; i++)
            add(gen.next());
    }

    // A generic convenience method:
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
} // /:~
