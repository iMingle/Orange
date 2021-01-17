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

package org.orange.arithmetic.base;

import org.orange.arithmetic.util.RandomUtil;
import org.orange.arithmetic.util.StandardDraw;

public class VisualCounter {
    
    private int count;
    private int N;
    private int max;
    
    public VisualCounter(int n, int max) {
        N = n;
        this.max = max;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void increment() {
        if (N > 0) {
            count++;
            if (count > max) {
                count = max;
            }
            N--;
        }
    }
    
    public void decrement() {
        if (N > 0) {
            count--;
            if (Math.abs(count) > max) {
                count = -max;
            }
            N--;
        }
    }
    
    public static void main(String[] args) {
        VisualCounter v = new VisualCounter(10, 5);
        int temp = 0;
        StandardDraw.setCanvasSize(800, 600);
        StandardDraw.setXscale(0, 100);
        StandardDraw.setYscale(0, 100);
        StandardDraw.setPenRadius(.005);
        for (int i = 0; i < 20; i++) {
            temp = RandomUtil.uniform(2);
            
            if (0 == temp) {
                v.increment();
            } else if (1 == temp) {
                v.decrement();
            }
            
            StandardDraw.rectangle(30, 30, 10, v.getCount() + 20);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            StandardDraw.clear();
        }
        
    }

}
