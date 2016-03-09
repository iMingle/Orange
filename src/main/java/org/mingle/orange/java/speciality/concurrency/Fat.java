/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 构造代码高昂的对象
 * 
 * @since 1.0
 * @author Mingle
 */
public class Fat {
    @SuppressWarnings("unused")
    private volatile double d;    // 阻止优化
    private static int counter = 0;
    private final int id = counter++;
    
    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double)i;
        }
    }
    
    public void operation() {
        System.out.println(this);
    }
    
    public String toString() {
        return "Fat id: " + id;
    }
}
