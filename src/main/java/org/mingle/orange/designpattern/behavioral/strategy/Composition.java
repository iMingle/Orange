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

package org.mingle.orange.designpattern.behavioral.strategy;

import java.awt.Component;

/**
 * 文档(包含正文和图形)
 * 
 * @author mingle
 */
public class Composition {
    @SuppressWarnings("unused") private final Compositor compositor;
    @SuppressWarnings("unused") private Component components; // the list of components
    @SuppressWarnings("unused") private int componentCount; // the number of components
    @SuppressWarnings("unused") private int lineWidth; // the Composition's line width
    @SuppressWarnings("unused") private int lineBreaks; // the position of linebreaks in components
    @SuppressWarnings("unused") private int lineCount; // the number of lines
    
    public Composition(Compositor compositor) {
        super();
        this.compositor = compositor;
    }
    
    public void repair() {
        
    }
}
