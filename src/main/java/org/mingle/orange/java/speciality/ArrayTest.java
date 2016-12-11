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

package org.mingle.orange.java.speciality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 数组测试类
 * @since 1.0
 * @author Mingle
 */
public class ArrayTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.asList(args, 1, 2, 3).getClass());    // class java.util.Arrays$ArrayList
    }

}

class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    
    public String toString() {
        return "Sphere " + id;
    }
}

/**
 * 数组和容器的比较
 */
class ContainerComparison {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = new BerylliumSphere[10];
        for (int i = 0; i < 5; i++) {
            spheres[i] = new BerylliumSphere();
        }
        
        System.out.println(Arrays.toString(spheres));
        // [Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4, null, null, null, null, null]
        System.out.println(spheres[4]);    //Sphere 4
        
        List<BerylliumSphere> sphereList= new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            sphereList.add(new BerylliumSphere());
        }
        
        System.out.println(sphereList);    // [Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]
        System.out.println(sphereList.get(4));    // Sphere 9
        
        int[] integers = {0, 1, 2, 3, 4, 5};
        
        System.out.println(Arrays.toString(integers));    // [0, 1, 2, 3, 4, 5]
        System.out.println(integers[4]);    // 4
        
        List<Integer> intList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        intList.add(97);
        System.out.println(intList);    // [0, 1, 2, 3, 4, 5, 97]
        System.out.println(intList.get(4));    // 4
    }
}

/**
 * 数组的初始化
 */
class ArrayOptions {
    public static void main(String[] args) {
        BerylliumSphere[] a;
        BerylliumSphere[] b = new BerylliumSphere[5];
        System.out.println("b: " + Arrays.toString(b));    // b: [null, null, null, null, null]
        
        BerylliumSphere[] c = new BerylliumSphere[4];
        for (int i = 0; i < c.length; i++) {
            if (c[i] == null)
                c[i] = new BerylliumSphere();
        }
        
        BerylliumSphere[] d = {
                new BerylliumSphere(),
                new BerylliumSphere(),
                new BerylliumSphere()
        };
        
        a = new BerylliumSphere[] {
                new BerylliumSphere(),
                new BerylliumSphere(),
        };
        System.out.println("a.length = " + a.length);    // a.length = 2
        System.out.println("b.length = " + b.length);    // b.length = 5
        System.out.println("c.length = " + c.length);    // c.length = 4
        System.out.println("d.length = " + d.length);    // d.length = 3
        a = d;
        System.out.println("a.length = " + a.length);    // a.length = 3
        
        int[] e;
//        System.out.println("e.length = " + e.length);    // 未初始化
        int[] f = new int[5];
        System.out.println("f: " + Arrays.toString(f));    // f: [0, 0, 0, 0, 0]
        
        int[] g = new int[4];
        for (int i = 0; i < g.length; i++) {
            g[i] = i * i;
        }
        
        int[] h = {
                11, 47, 93 
        };
        System.out.println("f.length = " + f.length);    // f.length = 5
        System.out.println("g.length = " + g.length);    // g.length = 4
        System.out.println("h.length = " + h.length);    // h.length = 3
        e = h;
        System.out.println("e.length = " + e.length);    // e.length = 3
        e = new int[] {
            1, 2    
        };
        System.out.println("e.length = " + e.length);    // e.length = 2
    }
}

/**
 * 返回一个数组
 */
class IceCream {
    private static Random rand = new Random(47);
    static final String[] FLAVORS = {
        "Chocolate", "Strawberry", "Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge",
        "Rum Raisin", "Praline Cream", "Mud Pie"
    };
    
    public static String[] flavorSet(int n) {
        if (n > FLAVORS.length)
            throw new IllegalArgumentException("Set too big");
        String[] results = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];
        for (int i = 0; i < n; i++) {
            int t;
            do {
                t = rand.nextInt(FLAVORS.length);
            } while (picked[t]);
            results[i] = FLAVORS[t];
            picked[t] = true;
        }
        
        return results;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.toString(flavorSet(3)));
        }
    }
}

/**
 * 多维基本类型数组
 */
class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                {
                    1, 2, 3
                },
                {
                    4, 5, 6 
                }
        };
        System.out.println(Arrays.deepToString(a));    // [[1, 2, 3], [4, 5, 6]]
    }
}

/**
 * 用new创建三维数组
 */
class ThreeDWithNew {
    public static void main(String[] args) {
        int[][][] a = new int[2][2][4];
        System.out.println(Arrays.deepToString(a));
        // [[[0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0]]]
    }
}

/**
 * 粗糙数组
 */
class RaggedArray {
    public static void main(String[] args) {
        Random rand = new Random(47);
        int[][][] a = new int[rand.nextInt(7)][][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = new int[rand.nextInt(5)];
            }
        }
        System.out.println(Arrays.deepToString(a));
        // [[], [[0], [0], [0, 0, 0, 0]], [[], [0, 0], [0, 0]], [[0, 0, 0], [0], [0, 0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0], []], [[0], [], [0]]]
    }
}

/**
 * 数组和泛型
 */
class ClassParameter<T> {
    public T[] f(T[] arg) {
        return arg;
    }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

class ParameterizedArrayType {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles2 = new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles = MethodParameter.f(doubles);
    }
}

/**
 * 泛型数组
 */
class ArrayOfGenerics {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = la;
        ls[0] = new ArrayList<String>();
//        ls[1] = new ArrayList<Integer>();    // Compile Error
        
        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();
    }
}

class ArrayOfGenericType<T> {
    T[] array;

    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
//        array = new T[size];    // Error
        array = (T[]) new Object[size];
    }
    
//    public <U> U[] makeArray() { return new U[10]; }
}

/**
 * 数据生成器
 */
class CountingGenerator {
    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value = false;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Boolean next() {
            value = !value;
            return value;
        }
        
    }
    
    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Byte next() {
            return value++;
        }
        
    }
    
    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" + 
                            "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    public static class Character implements Generator<java.lang.Character> {
        private int index = -1;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Character next() {
            index = (index + 1) % chars.length;
            return chars[index];
        }
        
    }
    
    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> cg = new Character();

        public String() {}

        public String(int length) {
            this.length = length;
        }

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < buf.length; i++) {
                buf[i] = cg.next();
            }
            return new java.lang.String(buf);
        }
        
    }
    
    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Short next() {
            return value++;
        }
        
    }
    
    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Integer next() {
            return value++;
        }
        
    }
    
    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Long next() {
            return value++;
        }
        
    }
    
    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Float next() {
            float result = value;
            value += 1.0;
            return result;
        }
        
    }
    
    public static class Double implements Generator<java.lang.Double> {
        private double value = 0;

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Double next() {
            double result = value;
            value += 1.0;
            return result;
        }
        
    }
}

class GeneratorsTest {
    public static int size = 10;
    public static void test(Class<?> surroundingClass) {
        for (Class<?> type : surroundingClass.getClasses()) {
            System.out.println(type.getSimpleName() + ": ");
            try {
                Generator<?> g = (Generator<?>) type.newInstance();
                for (int i = 0; i < size; i++)
                    System.out.printf(g.next() + " ");
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void main(String[] args) {
        test(CountingGenerator.class);
    }
}

/**
 * 使用随机数生成对象
 */
class RandomGenerator {
    private static Random r = new Random(47);
    
    public static class Boolean implements Generator<java.lang.Boolean> {
        
        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Boolean next() {
            return r.nextBoolean();
        }
        
    }
    
    public static class Byte implements Generator<java.lang.Byte> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Byte next() {
            return (byte) r.nextInt();
        }
        
    }
    
    public static class Character implements Generator<java.lang.Character> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Character next() {
            return CountingGenerator.chars[r.nextInt(CountingGenerator.chars.length)];
        }
        
    }
    
    public static class String extends CountingGenerator.String {
        {
            cg = new Character();
        }

        public String() {}

        public String(int length) {
            super(length);
        }

    }
    
    public static class Short implements Generator<java.lang.Short> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Short next() {
            return (short) r.nextInt();
        }
        
    }
    
    public static class Integer implements Generator<java.lang.Integer> {
        private int mod = 10000;
        
        public Integer() {}
        
        public Integer(int modulo) {
            this.mod = modulo;
        }

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Integer next() {
            return r.nextInt(mod);
        }
        
    }
    
    public static class Long implements Generator<java.lang.Long> {
        private int mod = 10000;
        
        public Long() {}
        
        public Long(int modulo) {
            this.mod = modulo;
        }

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Long next() {
            return new java.lang.Long(r.nextInt(mod));
        }
        
    }
    
    public static class Float implements Generator<java.lang.Float> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Float next() {
            int trimmed = Math.round(r.nextFloat() * 100);
            return ((float) trimmed) / 100;
        }
        
    }
    
    public static class Double implements Generator<java.lang.Double> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.Generator#next()
         */
        @Override
        public java.lang.Double next() {
            long trimmed = Math.round(r.nextDouble() * 100);
            return ((double) trimmed) / 100;
        }
        
    }
}

class RandomGeneratorsTest {
    public static void main(String[] args) {
        GeneratorsTest.test(RandomGenerator.class);
    }
}

/**
 * 从Generator创建数组
 */
class Generated {
    private static class CollectionData<T> extends ArrayList<T> {
        private static final long serialVersionUID = 8641177699553864709L;

        public CollectionData(Generator<T> gen, int quantity) {
            for (int i = 0; i < quantity; i++)
                add(gen.next());
        }

        // A generic convenience method:
        @SuppressWarnings("unused")
        public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
            return new CollectionData<T>(gen, quantity);
        }
    }
    
    public static <T> T[] array(T[] a, Generator<T> gen) {
        return new CollectionData<T>(gen, a.length).toArray(a);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
        T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<T>(gen, size).toArray(a);
    }
}

class GeneratedTest {
    public static void main(String[] args) {
        Integer[] a = { 9, 8, 7, 6 };
        System.out.println(Arrays.toString(a));
        
        a = Generated.array(a, new CountingGenerator.Integer());
        System.out.println(Arrays.toString(a));
        
        Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        System.out.println(Arrays.toString(b));
    }
}

/**
 * 包装器数组转换器
 */
class ConvertTo {
    public static boolean[] primitive(Boolean[] in) {
        boolean[] result = new boolean[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static char[] primitive(Character[] in) {
        char[] result = new char[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static byte[] primitive(Byte[] in) {
        byte[] result = new byte[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static short[] primitive(Short[] in) {
        short[] result = new short[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static int[] primitive(Integer[] in) {
        int[] result = new int[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static long[] primitive(Long[] in) {
        long[] result = new long[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static float[] primitive(Float[] in) {
        float[] result = new float[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
    
    public static double[] primitive(Double[] in) {
        double[] result = new double[in.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = in[i];
        }
        return result;
    }
}

/**
 * 基本类型转换
 */
class PrimitiveConversionDemonstration {
    public static void main(String[] args) {
        Integer[] a = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        int[] b = ConvertTo.primitive(a);
        System.out.println(Arrays.toString(b));
        
        boolean[] c = ConvertTo.primitive(Generated.array(Boolean.class, new CountingGenerator.Boolean(), 7));
        System.out.println(Arrays.toString(c));
    }
}

class CopyingArrays {
    public static void main(String[] args) {
        int[] i = new int[7];
        int[] j = new int[10];
        
        Arrays.fill(i, 47);
        Arrays.fill(j, 99);
        
        System.out.println("i = " + Arrays.toString(i));    // i = [47, 47, 47, 47, 47, 47, 47]
        System.out.println("j = " + Arrays.toString(j));    // j = [99, 99, 99, 99, 99, 99, 99, 99, 99, 99]
        
        System.arraycopy(i, 0, j, 0, i.length);
        System.out.println("j = " + Arrays.toString(j));    // j = [47, 47, 47, 47, 47, 47, 47, 99, 99, 99]
        
        int[] k = new int[5];
        Arrays.fill(k, 103);
        
        System.arraycopy(i, 0, k, 0, k.length);    // k = [47, 47, 47, 47, 47]
        System.out.println("k = " + Arrays.toString(k));
        Arrays.fill(k, 103);
        System.arraycopy(k, 0, i, 0, k.length);
        System.out.println("i = " + Arrays.toString(i));    // i = [103, 103, 103, 103, 103, 47, 47]
        
        Integer[] u = new Integer[10];
        Integer[] v = new Integer[5];
        Arrays.fill(u, new Integer(47));
        Arrays.fill(v, new Integer(99));
        
        System.out.println("u = " + Arrays.toString(u));    // u = [47, 47, 47, 47, 47, 47, 47, 47, 47, 47]
        System.out.println("v = " + Arrays.toString(v));    // v = [99, 99, 99, 99, 99]
        
        System.arraycopy(v, 0, u, u.length / 2, v.length);
        System.out.println("u = " + Arrays.toString(u));    // u = [47, 47, 47, 47, 47, 99, 99, 99, 99, 99]
    }
}

