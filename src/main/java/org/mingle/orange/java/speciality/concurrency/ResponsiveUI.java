/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

/**
 * 有响应的用户界面
 *
 * @since 1.8
 * @author Mingle
 */
class UnresponsiveUI {
    private volatile double d = 1;
    
    public UnresponsiveUI() throws Exception {
        while (d > 0)
            d += (Math.PI + Math.E) / d;
        System.in.read();    // 永远不会执行到这里
    }
}

public class ResponsiveUI extends Thread {
    private static volatile double d = 1;
    
    public ResponsiveUI() {
        setDaemon(true);
        start();
    }
    
    public void run() {
        while (d > 0)
            d += (Math.PI + Math.E) / d;
    }

    public static void main(String[] args) throws Exception {
//        new UnresponsiveUI();
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }

}
