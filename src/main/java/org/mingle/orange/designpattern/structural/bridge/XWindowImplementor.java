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

package org.mingle.orange.designpattern.structural.bridge;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 生成X窗口
 * 
 * @author mingle
 */
public class XWindowImplementor implements WindowImplementor {

    @Override public void top() {}

    @Override public void botton() {}

    @Override public void setOrigin(Point origin) {}

    @Override public void setExtent(Point extent) {}

    @Override public void deviceRect(int x, int y, int width, int height) {}

    @Override public void deviceText(String text, int x, int y) {}

    @Override public void deviceBitmap(String bitmap, int x, int y) {}

}
