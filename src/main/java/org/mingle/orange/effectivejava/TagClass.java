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

/**
 * 类层次优于标签类
 * 
 * @author Mingle
 */
public class TagClass {}

/**
 * 标签类,冗余信息太多
 */
class Figure {
    enum Shape {
        RECTANGLE, CIRCLE
    }
    
    final Shape shape;
    
    double length;
    double width;
    
    double radius;

    public Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    public Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }
    
    public double area() {
        switch (shape) {
        case RECTANGLE:
            return length * width;
        case CIRCLE:
            return Math.PI * (radius * radius);
        default:
            throw new AssertionError();
        }
    }
}

abstract class FigureBetter {
    abstract double area();
}

class Circle extends FigureBetter {
    final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    
    public double area() {
        return Math.PI * (radius * radius);
    }
}

class Rectangle extends FigureBetter {
    final double length;
    final double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    public double area() {
        return length * width;
    }
}
