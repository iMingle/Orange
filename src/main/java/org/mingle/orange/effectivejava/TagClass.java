/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

/**
 * 类层次优于标签类
 * 
 * @since 1.8
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