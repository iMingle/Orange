/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.adapter;

/**
 * 文本视图,由原点,宽度,高度定义
 * 
 * @since 1.0
 * @author Mingle
 */
public interface TextView {
    void origin(Integer x, Integer y);
    void extent(Integer width, Integer height);
    boolean isEmpty();
}
