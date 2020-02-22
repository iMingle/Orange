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

package org.orange.java.concurrent;

public class ProducerConsumer {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Producer p = new Producer(ss);
        Consumer c = new Consumer(ss);
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(c).start();
    }
}

class WoTou {
    int id; 
    WoTou(int id) {
        this.id = id;
    }
    public String toString() {
        return "WoTou : " + id;
    }
}

class SyncStack {
    int index = 0;
    WoTou[] arrWT = new WoTou[6];
    
    public synchronized void push(WoTou wt) {
        while(index == arrWT.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();        
        arrWT[index] = wt;
        index ++;
    }
    
    public synchronized WoTou pop() {
        while(index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        index--;
        return arrWT[index];
    }
}

class Producer implements Runnable {
    SyncStack ss = null;
    Producer(SyncStack ss) {
        this.ss = ss;
    }
    
    public void run() {
        for(int i=0; i<20; i++) {
            WoTou wt = new WoTou(i);
            ss.push(wt);
System.out.println("生产了：" + wt);
            try {
                Thread.sleep((int)(Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }
    }
}

class Consumer implements Runnable {
    SyncStack ss = null;
    Consumer(SyncStack ss) {
        this.ss = ss;
    }
    
    public void run() {
        for(int i=0; i<20; i++) {
            WoTou wt = ss.pop();
System.out.println("消费了: " + wt);
            try {
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }
    }
}