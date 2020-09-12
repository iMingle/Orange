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

package org.orange.effectivejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author mingle
 */
public class Generic<E> {
    private final List<E> add = new ArrayList<>();

    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(2);
        Set<Double> doubles = new HashSet<>();
        doubles.add(1.0);
        doubles.add(2.0);

        Set<Number> numbers = union(integers, doubles);
        System.out.println(numbers);
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            add.add(e);
        }
    }

    public void popAll(Collection<? super E> dst) {
        dst.add(pop());
    }

    private E pop() {
        return add.get(0);
    }

    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);

        return result;
    }

    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        Iterator<? extends T> it = list.iterator();
        T result = it.next();
        while (it.hasNext()) {
            T t = it.next();
            if (t.compareTo(result) > 0)
                result = t;
        }

        return result;
    }

    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
