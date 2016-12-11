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
 * 自管理的线程
 *
 * @author mingle
 */
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);
    
    public SelfManaged() {
        t.start();
    }
    
    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + "), ";
    }
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SelfManaged();
    }

}
