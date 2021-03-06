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

package org.orange.util.benchmark;

/**
 * 逆优化
 * 
 * @author mingle
 */
public class Deoptimization {
    private static final long ITERATIONS = 5000000000L;
    private static final long NANOS_PER_MS = 1000L * 1000L;
    private static final StringBuilder sb = new StringBuilder();

    private static long doTest(String str, Shape s, long iterations) {
        double area = 0;
        long startTime = System.nanoTime();

        for (long i = 0; i < iterations; i++)
            area = s.area();

        long elapsedTime = System.nanoTime() - startTime;
        sb.append(str).append(area);
        System.out.println(sb.toString());
        sb.setLength(0);

        return elapsedTime;
    }

    private static void printStats(String s, long n, long elapsedTime) {
        float millis = elapsedTime / NANOS_PER_MS;
        float rate = 0;
        if (millis != 0)
            rate = n / millis;
        System.out.println(s + ": Elapsed time in ms -> " + millis);
        System.out.println(s + ": Iterations per ms --> " + rate);
    }

    public static void main(String[] args) {
        String areaStr = "  Area: ";
        Shape s = new Square(25.33);
        Shape r = new Rectangle(20.75, 30.25);
        Shape rt = new RightTriangle(20.50, 30.25);

        System.out.println("Warming up ...");
        long elapsedTime = doTest(areaStr, s, ITERATIONS);
        printStats("    Square", ITERATIONS, elapsedTime);
        elapsedTime = doTest(areaStr, r, ITERATIONS);
        printStats("    Rectangle", ITERATIONS, elapsedTime);
        elapsedTime = doTest(areaStr, rt, ITERATIONS);
        printStats("    RightTriangle", ITERATIONS, elapsedTime);
        System.out.println("1st warmup done.");

        System.out.println("Starting 2nd Warm up ...");
        elapsedTime = doTest(areaStr, s, ITERATIONS);
        printStats("    Square", ITERATIONS, elapsedTime);
        elapsedTime = doTest(areaStr, r, ITERATIONS);
        printStats("    Rectangle", ITERATIONS, elapsedTime);
        elapsedTime = doTest(areaStr, rt, ITERATIONS);
        printStats("    RightTriangle", ITERATIONS, elapsedTime);
        System.out.println("2st Warmup done.");

        System.out.println("Starting measurement interval ...");
        elapsedTime = doTest(areaStr, s, ITERATIONS);
        printStats("    Square", ITERATIONS, elapsedTime);
        elapsedTime = doTest(areaStr, r, ITERATIONS);
        printStats("    Rectangle", ITERATIONS, elapsedTime);
        elapsedTime = doTest(areaStr, rt, ITERATIONS);
        printStats("    RightTriangle", ITERATIONS, elapsedTime);
        System.out.println("Measurement interval done.");
    }
}

interface Shape {
    double area();
}

class Square implements Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double area() {
        return side * side;
    }
}

class Rectangle implements Shape {
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }
}

class RightTriangle implements Shape {
    private final double base;
    private final double height;

    public RightTriangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double area() {
        return .5 * base * height;
    }
}
