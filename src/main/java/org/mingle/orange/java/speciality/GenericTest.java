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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * 泛型的测试类
 *
 * @since 1.0
 * @author Mingle
 */
public class GenericTest {
    public static void main(String[] args) {}
}

/**
 * 二元组
 */
class TwoTuple<U,V> {
    public final U first;
    public final V second;
    
    /**
     * @param a
     * @param b
     */
    public TwoTuple(U a, V b) {
        super();
        this.first = a;
        this.second = b;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TwoTuple [first=" + first + ", second=" + second + "]";
    }
}

/**
 * 三元组
 */
class ThreeTuple<U,V,W> extends TwoTuple<U,V> {
    public final W third;

    /**
     * @param a
     * @param b
     */
    public ThreeTuple(U a, V b, W c) {
        super(a, b);
        this.third = c;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ThreeTuple [first=" + first + ", second=" + second + " + third=" + third + "]";
    }

}

/**
 * 用链表实现的Stack
 */
class LinkedStack<T> {
    private Node<T> top = new Node<T>();
    
    public void push(T item) {
        top = new Node<T>(item, top);
    }
    
    public T pop() {
        T result = top.item;
        if (!top.end())
            top = top.next;
        return result;
    }
    
    private static class Node<U> {
        U item;
        Node<U> next;
        
        Node() {
            item = null;
            next = null;
        }

        /**
         * @param item
         * @param next
         */
        public Node(U item, Node<U> next) {
            super();
            this.item = item;
            this.next = next;
        }
        
        boolean end() {
            return item == null && next == null;
        }
    }
    
    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<String>();
        for (String s : "Phasers on stun!".split(" ")) {
            lss.push(s);
        }
        String s = null;
        while ((s = lss.pop()) != null) {
            System.out.println(s);
        }
    }
}

/**
 * 泛型接口
 */
interface Generator<T> {
    T next();
}

class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

class Latte extends Coffee {}

class Mocha extends Coffee {}

class Cappuccino extends Coffee {}

class Americano extends Coffee {}

class Breve extends Coffee {}

class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class<?>[] types = {
            Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class
    };
    private Random rand = new Random(47);
    private int size = 0;
    
    public CoffeeGenerator() {}
    
    public CoffeeGenerator(int sz) {
        this.size = sz;
    }
    
    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.Generator#next()
     */
    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            return count > 0;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#next()
         */
        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }
        
    }
    
    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }
    }
}

/**
 * 创建常用的容器对象
 */
class New {
    public static <K,V> Map<K,V> map() {
        return new HashMap<>();
    }
    
    public static <T> List<T> list() {
        return new ArrayList<>();
    }
    
    public static <T> List<T> lList() {
        return new LinkedList<>();
    }
    
    public static <T> Set<T> set() {
        return new HashSet<>();
    }
    
    public static <T> Queue<T> queue() {
        return new LinkedList<>();
    }
}

/**
 * 类型擦除,边界处的动作
 */
class ArrayMaker<T> {
    private Class<T> kind;

    /**
     * @param kind
     */
    public ArrayMaker(Class<T> kind) {
        super();
        this.kind = kind;
    }
    
    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }
    
    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<String>(String.class);
        String[] stringArray = stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));
    }
}

class ListMaker<T> {
    List<T> create() {
        return new ArrayList<T>();
    }
    
    public static void main(String[] args) {
        ListMaker<String> stringMaker = new ListMaker<String>();
        @SuppressWarnings("unused")
        List<String> stringList = stringMaker.create();
    }
}

class FilledListMaker<T> {
    List<T> create(T t, int n) {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < n; i++) {
            result.add(t);
        }
        return result;
    }
    
    public static void main(String[] args) {
        FilledListMaker<String> stringMaker = new FilledListMaker<String>();
        List<String> list = stringMaker.create("Hello", 4);
        System.out.println(list);
    }
}

/**
 * 擦除的补偿
 */
class Erased<T> {
    private final int SIZE = 100;
    
    /**
     * 运行时无法知道确切类型
     * @param arg
     */
    public void f(Object arg) {
//        if (arg instanceof T) {}    // Error
//        T var = new T();            // Error
//        T[] array = new T[SIZE];    // Error
        @SuppressWarnings({ "unchecked", "unused" })
        T[] array = (T[]) new Object[SIZE];
    }
}

class Building {}

class House extends Building {}

/**
 * 使用类型标签,可以使用动态的isInstance()
 */
class ClassTypeCapture<T> {
    Class<T> kind;

    /**
     * @param kind
     */
    public ClassTypeCapture(Class<T> kind) {
        super();
        this.kind = kind;
    }
    
    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }
    
    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<Building>(Building.class);
        System.out.println(ctt1.f(new Building()));        // true
        System.out.println(ctt1.f(new House()));        // true
        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<House>(House.class);
        System.out.println(ctt2.f(new Building()));        // false
        System.out.println(ctt2.f(new House()));        // true
    }
}

/**
 * 创建类型实例
 */
class ClassAsFactory<T> {
    T t;

    /**
     * 必须有默认构造方法
     * @param t
     */
    public ClassAsFactory(Class<T> kind) {
        super();
        try {
            this.t = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ClassAsFactory<String> fs = new ClassAsFactory<String>(String.class);
        /* Integer没有默认构造方法 */
        ClassAsFactory<Integer> fi = new ClassAsFactory<Integer>(Integer.class);
    }
}

/**
 * 显示工厂
 */
interface FactoryI<T> {
    T create();
}

class Foo<T> {
    @SuppressWarnings("unused")
    private T x;
    
    public <F extends FactoryI<T>> Foo(F factory) {
        x = factory.create();
    }
}

class IntegerFactory implements FactoryI<Integer> {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.GFactory#create()
     */
    @Override
    public Integer create() {
        return new Integer(0);
    }
    
}

class Widget {
    public static class Factory implements FactoryI<Widget> {

        /* (non-Javadoc)
         * @see org.mingle.orange.java.speciality.FactoryI#create()
         */
        @Override
        public Widget create() {
            return new Widget();
        }
        
    }
}

class FactoryConstraint {
    public static void main(String[] args) {
        new Foo<Integer>(new IntegerFactory());
        new Foo<Widget>(new Widget.Factory());
    }
}

/**
 * 模板方法实现的工厂
 */
abstract class GenericWithCreate<T> {
    final T element;

    /**
     * @param element
     */
    public GenericWithCreate() {
        this.element = create();
    }
    
    abstract T create();
}

class X {}

class Creator extends GenericWithCreate<X> {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.GenericWithCreate#create()
     */
    @Override
    X create() {
        return new X();
    }
    
    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

class CreatorGeneric {
    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();        // X
    }
}

/**
 * 边界测试
 */
interface SuperPower {}

/**
 * X射线
 */
interface XRayVision extends SuperPower {
    void seeThroughWalls();
}

/**
 * 顺风耳
 */
interface SuperHearing extends SuperPower {
    void hearSubtleNoises();
}

/**
 * 狗鼻子
 */
interface SuperSmell extends SuperPower {
    void trackBySmell();
}

/**
 * 超级英雄
 */
class SuperHero<POWER extends SuperPower> {
    POWER power;

    /**
     * @param power
     */
    public SuperHero(POWER power) {
        super();
        this.power = power;
    }
    
    public POWER getPower() {
        return power;
    }
}

/**
 * 超级侦探
 */
class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {

    /**
     * @param power
     */
    public SuperSleuth(POWER power) {
        super(power);
    }
    
    void see() {
        power.seeThroughWalls();
    }
}

/**
 * 狗英雄
 */
class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {

    /**
     * @param power
     */
    public CanineHero(POWER power) {
        super(power);
    }
    
    void hear() {
        power.hearSubtleNoises();
    }
    
    void smell() {
        power.trackBySmell();
    }
}

class SuperHearSmell implements SuperHearing, SuperSmell {

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.SuperSmell#trackBySmell()
     */
    @Override
    public void trackBySmell() {}

    /* (non-Javadoc)
     * @see org.mingle.orange.java.speciality.SuperHearing#hearSubtleNoises()
     */
    @Override
    public void hearSubtleNoises() {}
    
}

class DogBoy extends CanineHero<SuperHearSmell> {

    /**
     * @param power
     */
    public DogBoy() {
        super(new SuperHearSmell());
    }
    
}

/**
 * 通配符测试,水果
 */
class Fruit {}

class Apple extends Fruit {}

class Jonathan extends Apple {}

class Orange extends Fruit {}

/**
 * 协变数组
 */
class CovariantArrays {
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();
        fruit[1] = new Jonathan();
        
        try {
            fruit[0] = new Fruit();
        } catch (Exception e) {
            System.out.println(e);    // java.lang.ArrayStoreException: org.mingle.orange.java.speciality.Fruit
        }
        
        try {
            fruit[0] = new Orange();
        } catch (Exception e) {
            System.out.println(e);    // java.lang.ArrayStoreException: org.mingle.orange.java.speciality.Orange
        }
    }
}

class NonCovariantGenerics {
//    List<Fruit> flist = new ArrayList<Apple>();    // Compile Error
}

class GenericAndCovariance {
    public static void main(String[] args) {
        List<? extends Fruit> flist = new ArrayList<Apple>();
//        flist.add(new Apple());        // Compile Error
//        flist.add(new Fruit());        // Compile Error
//        flist.add(new Object());    // Compile Error
        flist.add(null);
    }
}

/**
 * 编译器的智力
 */
class CompilerIntelligence {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<? extends Fruit> flist = Arrays.asList(new Apple());
        Apple a = (Apple) flist.get(0);
        flist.contains(new Apple());
        flist.indexOf(new Apple());
    }
}

class Holder<T> {
    private T value;

    public Holder() {
        super();
    }

    /**
     * @param value
     */
    public Holder(T value) {
        super();
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    public boolean equals(Object obj) {
        return value.equals(obj);
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<Apple>(new Apple());
        Apple d = apple.getValue();
        apple.setValue(d);
//        Holder<Fruit> fruit = apple;    // Compile Error
        Holder<? extends Fruit> fruit = apple;
        Fruit p = fruit.getValue();
        d= (Apple) fruit.getValue();
        
        try {
            Orange c = (Orange) fruit.getValue();
        } catch (Exception e) {
            System.out.println(e);        // java.lang.ClassCastException
        }
        
//        fruit.setValue(new Apple());    // Compile Error
        
        System.out.println(fruit.equals(d));    // true
    }
}

/**
 * 超类型通配符
 */
class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
//        apples.add(new Fruit());    // Compile Error
    }
}

class GenericWriting {
    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }
    
    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();
    
    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruit, new Apple());
    }
    
    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }
    
    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }
    
    public static void main(String[] args) {
        f1();
        f2();
    }
}

class GenericReading {
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }
    
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());
    
    @SuppressWarnings("unused")
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }
    
    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }
    
    @SuppressWarnings("unused")
    static void f2() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruit);
//        Fruit a = fruitReader.readExact(apples);    // Compile Error
    }
    
    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }
    
    @SuppressWarnings("unused")
    static void f3() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
    }
    
    public static void main(String[] args) {
        f1(); f2(); f3();
    }
}

/**
 * 无界通配符<?>
 */
class UnboundedWildcards1 {
    @SuppressWarnings("rawtypes")
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    static void assign1(List list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }
    
    static void assign2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }
    
    static void assign3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        assign1(new ArrayList());
        assign2(new ArrayList());
        assign3(new ArrayList());
        
        assign1(new ArrayList<String>());
        assign2(new ArrayList<String>());
        assign3(new ArrayList<String>());
        
        List<?> wildlist = new ArrayList();
        assign1(wildlist);
        assign2(wildlist);
        assign3(wildlist);
    }
}

class UnboundedWildcards2 {
    @SuppressWarnings("rawtypes")
    static Map map1;
    static Map<?, ?> map2;
    static Map<String, ?> map3;
    
    @SuppressWarnings("rawtypes")
    static void assign1(Map map) {
        map1 = map;
    }
    
    static void assign2(Map<?, ?> map) {
        map2 = map;
    }
    
    static void assign3(Map<String, ?> map) {
        map3 = map;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        assign1(new HashMap());
        assign2(new HashMap());
        assign3(new HashMap());
        
        assign1(new HashMap<String, Integer>());
        assign2(new HashMap<String, Integer>());
        assign3(new HashMap<String, Integer>());
    }
}

/**
 * 通配符
 */
class Wildcards {
    /**
     * 原生类型，可以传递任何类型Object
     * @param holder
     * @param arg
     */
    @SuppressWarnings("unchecked")
    static void rawArgs(@SuppressWarnings("rawtypes") Holder holder, Object arg) {
        holder.setValue(arg);
        holder.setValue(new Wildcards());
        @SuppressWarnings("unused")
        Object obj = holder.getValue();
    }
    
    /**
     * 无界匹配,持有具有某种具体类型的同构集合，不能只向其传递Object
     * @param holder
     * @param arg
     */
    static void unboundedArg(Holder<?> holder, Object arg) {
//        holder.setValue(arg);                // Compile Error
//        holder.setValue(new Wildcards());    // Compile Error
        @SuppressWarnings("unused")
        Object obj = holder.getValue();
    }
    
    static <T> T exact1(Holder<T> holder) {
        T t = holder.getValue();
        return t;
    }
    
    /**
     * 确切的泛型类型
     * @param holder
     * @param arg
     * @return
     */
    static <T> T exact2(Holder<T> holder, T arg) {
        holder.setValue(arg);
        T t = holder.getValue();
        return t;
    }
    
    /**
     * 边界,为了防止将Orange放入Holder<Apple>中,set方法不允许,get返回T是基类型，没有问题
     * @param holder
     * @param arg
     * @return
     */
    static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
//        holder.setValue(arg);    // Compile Error
        T t = holder.getValue();
        return t;
    }
    
    /**
     * 逆变，可以工作于基类的对象都可以工作于导出类,尝试调用get是没有用的,因为holder持有的可以是任何超类型,唯一安全的类型是Object
     * @param holder
     * @param arg
     */
    static <T> void wildSupertype(Holder<? super T> holder, T arg) {
        holder.setValue(arg);
//        T t = holder.getValue();    // Compile Error
        @SuppressWarnings("unused")
        Object obj = holder.getValue();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    public static void main(String[] args) {
        Holder raw = new Holder<Long>();
        raw = new Holder();
        
        Holder<Long> qualified = new Holder<Long>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        
        Long lng = 1L;
        
        rawArgs(raw, lng);
        rawArgs(qualified, lng);
        rawArgs(unbounded, lng);
        rawArgs(bounded, lng);
        
        unboundedArg(raw, lng);
        unboundedArg(qualified, lng);
        unboundedArg(unbounded, lng);
        unboundedArg(bounded, lng);
        
        Object r1 = exact1(raw);
        Long r2 = exact1(qualified);
        Object r3 = exact1(unbounded);
        Long r4 = exact1(bounded);
        
        Long r5 = exact2(raw, lng);
        Long r6 = exact2(qualified, lng);
//        Long r7 = exact2(unbounded, lng);    // Compile Error
//        Long r8 = exact2(bounded, lng);        // Compile Error
        
        Long r9 = wildSubtype(raw, lng);
        Long r10 = wildSubtype(qualified, lng);
        Object r11 = wildSubtype(unbounded, lng);
        Long r12 = wildSubtype(bounded, lng);
    }
}