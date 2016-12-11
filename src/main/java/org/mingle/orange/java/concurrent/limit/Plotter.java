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

package org.mingle.orange.java.concurrent.limit;

import java.awt.Point;

/**
 * 跨方法的限制
 * 
 * @author mingle
 */
public class Plotter {
    public void showNextPoint() {
        Point p = new Point();
        p.x = computeX();
        p.y = computeY();
        display(p);
    }

    int computeX() {
        return 1;
    }

    int computeY() {
        return 1;
    }

    protected void display(Point p) {
        // somehow arrange to show p.
    }
}
