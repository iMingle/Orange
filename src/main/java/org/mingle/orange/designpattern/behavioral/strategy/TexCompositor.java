/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.designpattern.behavioral.strategy;

/**
 * 一次检查一个段落,并同时考虑到各Component的大小和伸展性
 * 
 * @author mingle
 */
public class TexCompositor implements Compositor {

    @Override public int compose(Coord[] natural, Coord[] stretch,
            Coord[] shrink, int componentCount, int lineWidth, int[] breaks) {
        return 0;
    }

}
