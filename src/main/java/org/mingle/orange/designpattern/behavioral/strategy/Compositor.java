/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.strategy;

/**
 * 排版策略
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Compositor {
    /**
     * 
     * @param natural 正常大小
     * @param stretch 可伸展性
     * @param shrink 可收缩性
     * @param componentCount Component的数目
     * @param lineWidth 线的宽度
     * @param breaks 数组
     * @return 换行数目
     */
    int compose(Coord natural[], Coord stretch[], Coord shrink[], 
            int componentCount, int lineWidth, int breaks[]);
}
