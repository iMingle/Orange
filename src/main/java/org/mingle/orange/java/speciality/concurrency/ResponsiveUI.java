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

package org.mingle.orange.java.speciality.concurrency;

/**
 * 有响应的用户界面
 *
 * @author mingle
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
