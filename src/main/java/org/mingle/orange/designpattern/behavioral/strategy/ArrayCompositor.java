/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.strategy;

/**
 * 用规则的间距将构件分割成行
 * 
 * @since 1.0
 * @author Mingle
 */
public class ArrayCompositor implements Compositor {

    @Override public int compose(Coord[] natural, Coord[] stretch,
            Coord[] shrink, int componentCount, int lineWidth, int[] breaks) {
        return 0;
    }

}
