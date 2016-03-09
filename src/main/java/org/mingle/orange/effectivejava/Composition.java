/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 复合设计
 * 
 * @since 1.0
 * @author Mingle
 */
public class Composition {

    public static void main(String[] args) {
        InstrumentedSetComposition<String> s = new InstrumentedSetComposition<String>(new TreeSet<String>());
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount());
    }

}

/**
 * 继承方式实现
 */
class InstrumentedHashSet<E> extends HashSet<E> {
    private static final long serialVersionUID = -3048767023300971996L;
    
    // The number of attempted element insertions
    private int addCount = 0;
    
    public InstrumentedHashSet() {}

    public InstrumentedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
    
    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    
    /**
     * addAll方法会调用add方法,导致计数错误
     */
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    
    public int getAddCount() {
        return addCount;
    }
}

/**
 * 组合方式实现
 */
class InstrumentedSetComposition<E> extends ForwardingSet<E> {
    private int addCount = 0;
    
    public InstrumentedSetComposition(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    
    public int getAddCount() {
        return addCount;
    }
}

/**
 * reusable forwarding class
 */
class ForwardingSet<E> implements Set<E> {
    private Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return s.iterator();
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    @Override
    public void clear() {
        s.clear();
    }
    
}