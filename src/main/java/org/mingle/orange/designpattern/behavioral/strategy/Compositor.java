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
 * 排版策略
 * 
 * @author mingle
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
