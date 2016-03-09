/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过该操作可以实现对集合(Collection)的并行处理和函数式操作.根据操作返回的结果不同,
 * 流式操作分为中间操作和最终操作两种.最终操作返回一特定类型的结果,而中间操作返回流本身,
 * 这样就可以将多个操作依次串联起来.根据流的并发性,流又可以分为串行和并行两种.
 * 
 * 流式操作实现了集合的过滤、排序、映射等功能.
 * Stream 和 Collection 集合的区别:Collection 是一种静态的内存数据结构,
 * 而 Stream 是有关计算的.前者是主要面向内存,存储在内存中,后者主要是面向 CPU,通过 CPU实现计算.
 * 
 * 串行和并行的流
 * 流有串行和并行两种,串行流上的操作是在一个线程中依次完成,而并行流则是在多个线程上同时执行.并行与串行的流可以相互切换:通过 stream.sequential() 返回串行的流,通过 stream.parallel() 返回并行的流.相比较串行的流,并行的流可以很大程度上提高程序的执行效率.
 * 
 * 中间操作
 * 该操作会保持 stream 处于中间状态,允许做进一步的操作.它返回的还是的 Stream,允许更多的链式操作.常见的中间操作有:
 * filter():对元素进行过滤;
 * sorted():对元素排序;
 * map():元素的映射;
 * distinct():去除重复元素;
 * subStream():获取子 Stream 等.
 * 
 * 终止操作
 * 该操作必须是流的最后一个操作,一旦被调用,Stream 就到了一个终止状态,而且不能再使用了.常见的终止操作有:
 * forEach():对每个元素做处理;
 * toArray():把元素导出到数组;
 * findFirst():返回第一个匹配的元素;
 * anyMatch():是否有匹配的元素等.
 * 
 * @since 1.0
 * @author Mingle
 */
public class CollectionStream {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        
        stringCollection.stream()
                        .sorted()
                        .filter((s) -> s.startsWith("a"))
                        .forEach(System.out::println);
        System.out.println("=======================");
        
        stringCollection.stream()
                        .map(String::toUpperCase)
                        .sorted()
                        .forEach(System.out::println);
        System.out.println("=======================");
        
        boolean matched = false;
        matched = stringCollection.stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(matched); // true
        System.out.println("=======================");
        
        matched = stringCollection.stream()
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println(matched); // false
        System.out.println("=======================");
        
        matched = stringCollection.stream()
                .noneMatch((s) -> s.startsWith("z"));
        System.out.println(matched); // true
        System.out.println("=======================");
        
        long count = 0;
        count = stringCollection.stream()
                .filter((s) -> s.startsWith("a"))
                .count();
        System.out.println(count); // 2
        System.out.println("=======================");
        
        Optional<String> reduced = stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println); // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
        System.out.println("=======================");
        
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        
        long t0 = System.nanoTime();
        count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis)); // 串行耗时: 2338 ms
        
        t0 = System.nanoTime();
        count = values.parallelStream().sorted().count();
        System.out.println(count);
        t1 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis)); // 并行排序耗时: 2183 ms
    }
}
