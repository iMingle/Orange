/*
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
 * limitations under the License.
 */

package org.orange.designpattern.structural.flyweight;

import java.awt.Font;

/**
 * 图形上下文,用于存储外部状态(字体)
 *
 * @author mingle
 */
public class GlyphContext {
    @SuppressWarnings("unused") private int index;
    @SuppressWarnings("unused") private BTree<Glyph, Font> fonts;

    public Font font() {
        return null;
    }

    public void setFont(Font font, int span) {

    }

    public Glyph current() {
        return null;
    }

    public void next(int step) {
        index++;
    }

    public void insert(int quantity) {

    }

}
