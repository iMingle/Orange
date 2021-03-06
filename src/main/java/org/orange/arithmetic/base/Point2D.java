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

package org.orange.arithmetic.base;

import org.orange.arithmetic.util.RandomUtil;
import org.orange.arithmetic.util.StandardDraw;

public class Point2D {
    private final double x;
    private final double y;
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double distance(Point2D point) {
        double dx = point.x - this.x;
        double dy = point.y - this.y;
        return Math.sqrt(dx * dx - dy * dy);
    }
    
    public void draw() {
        StandardDraw.point(this.x, this.y);
    }

    public static void main(String[] args) {
        StandardDraw.setCanvasSize(800, 600);
        StandardDraw.setXscale(0, 100);
        StandardDraw.setYscale(0, 100);
        StandardDraw.setPenRadius(.005);

        Point2D[] points = new Point2D[100];
        for (int i = 0; i < 100; i++) {
            int x = RandomUtil.uniform(50);
            int y = RandomUtil.uniform(50);
            System.out.print(x + ", ");
            System.out.println(y);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }
    }

}
