/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.strategy;

/**
 * 一次检查一个段落,并同时考虑到各Component的大小和伸展性
 * 
 * @since 1.0
 * @author Mingle
 */
public class TexCompositor implements Compositor {

    @Override public int compose(Coord[] natural, Coord[] stretch,
            Coord[] shrink, int componentCount, int lineWidth, int[] breaks) {
        return 0;
    }

}
