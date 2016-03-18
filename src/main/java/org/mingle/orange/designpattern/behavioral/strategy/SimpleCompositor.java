/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.strategy;

/**
 * 一次检查一行Component,并决定在那儿换行
 * 
 * @since 1.0
 * @author Mingle
 */
public class SimpleCompositor implements Compositor {

    @Override public int compose(Coord[] natural, Coord[] stretch,
            Coord[] shrink, int componentCount, int lineWidth, int[] breaks) {
        return 0;
    }

}
