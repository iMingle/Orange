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

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 银行出纳员仿真
 *
 * @author mingle
 */
// 只读对象,不需要同步
class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }
    
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

class CustomerLine extends ArrayBlockingQueue<Customer> {
    private static final long serialVersionUID = 4833902599074478444L;

    public CustomerLine(int capacity) {
        super(capacity);
    }
    
    public String toString() {
        if (this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for (Customer customer : this)
            result.append(customer);
        return result.toString();
    }
}

/**
 * 随机添加顾客到队列中
 */
class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);
    
    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.add(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
    
}

class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;
    private int customersServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
            try {
                while (!Thread.interrupted()) {
                    Customer customer = customers.take();
                    TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                    synchronized (this) {
                        customersServed++;
                        while (!servingCustomerLine)
                            wait();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(this + "interrupted");
            }
            System.out.println(this + "terminating");
    }
    
    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }
    
    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }
    
    public String toString() {
        return "Teller " + id + " ";
    }
    
    public String shortString() {
        return "T" + id;
    }
    
    @Override
    public synchronized int compareTo(Teller o) {
        return customersServed < o.customersServed ? -1 : (customersServed == o.customersServed ? 0 : 1);
    }
}

class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
    private int adjustmentPeriod;

    public TellerManager(ExecutorService exec, CustomerLine customers,
            int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // 用一个出纳员开始
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }
    
    public void adjustTellerNumber() {
        if (customers.size() / workingTellers.size() > 2) {
            if (tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
        }
        
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2)
            reassignOneTeller();
        // 无人排队,只需要一个出纳
        if (customers.size() == 0)
            while (workingTellers.size() > 1)
                reassignOneTeller();
    }
    
    /**
     * 给出纳员分配其他的任务
     */
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.println(customers + " { ");
                for (Teller teller : workingTellers)
                    System.out.println(teller.shortString() + " ");
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }
    
    public String toString() {
        return "TellerManager ";
    }
}

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMNET_PERIOD = 1000;

    public static void main(String[] args) throws NumberFormatException, InterruptedException, IOException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec, customers, ADJUSTMNET_PERIOD));
        if (args.length > 0)
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }

}
