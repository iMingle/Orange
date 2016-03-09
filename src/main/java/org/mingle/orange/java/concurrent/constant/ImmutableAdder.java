/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.constant;

/**
 * final不变性
 * 
 * @since 1.0
 * @author Mingle
 */
public class ImmutableAdder {
    private final int offset;

    public ImmutableAdder(int offset) {
        this.offset = offset;
    }

    public int addOffset(int b) {
        return offset + b;
    }
}
