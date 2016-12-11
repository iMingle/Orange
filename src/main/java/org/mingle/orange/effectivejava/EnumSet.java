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

package org.mingle.orange.effectivejava;

import java.util.Set;

/**
 * 用EnumSet代替位域
 * 
 * @author mingle
 */
public class EnumSet {
    class Text {
        public static final int STYLE_BOLD = 1 << 0;
        public static final int STYLE_ITALIC = 1 << 1;
        public static final int STYLE_UNDERLINE = 1 << 2;
        public static final int STYLE_STRIKETHROUGH = 1 << 3;
        
        public void applyStyle(int styles) {}
    }
    
    enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }
    
    static class TextEnumSet {
        public void applyStyle(Set<Style> styles) {}
    }
    
    public static void main(String[] args) {
        TextEnumSet text = new TextEnumSet();
        text.applyStyle(java.util.EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
