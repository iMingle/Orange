/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.java.speciality.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 分发工作,多个任务一起工作
 * 组装车子包括创建底盘,安装发送机,车厢和轮子
 * 
 * @author mingle
 */
class Car {
    private final int id;
    private boolean engine = false;
    private boolean driveTrain = false;
    private boolean wheels = false;
    
    public Car() {
        id = -1;
    }
    
    public Car(int id) {
        this.id = id;
    }
    
    public synchronized int getId() {
        return id;
    }
    
    public synchronized void addEngine() {
        engine = true;
    }
    
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }
    
    public synchronized void addWheels() {
        wheels = true;
    }
    
    public synchronized String toString() {
        return "Car " + id + " [" + " Engine: " + engine + " driveTrain: " + driveTrain + " wheels: " + wheels + " ]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car> {
    private static final long serialVersionUID = 218047965898621836L;
}

/**
 * 底盘
 */
class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;
    
    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car c = new Car(counter++);
                System.out.println("ChassisBuilder created " + c);
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

/**
 * 装配
 */
class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;
    
    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue,
            RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }
    
    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 阻塞直到底盘可用
                car = chassisQueue.take();
                // 雇佣机器人开始工作
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                // 直到所有的机器人完成工作
                barrier.await();
                // 将car加入完成队列
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
    
}

class Reporter implements Runnable {
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
    
}

abstract class Robot implements Runnable {
    private RobotPool pool;
    protected Assembler assembler;
    private boolean engage = false;
    
    public Robot(RobotPool pool) {
        this.pool = pool;
    }
    
    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }
    
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }
    
    abstract protected void performService();

    @Override
    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();    // 同步
                powerDown();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        pool.release(this);
        while (engage == false)
            wait();
    }
    
    public String toString() {
        return getClass().getName();
    }
}

class EngineRobot extends Robot {

    public EngineRobot(RobotPool pool) {
        super(pool);
    }
    
    @Override
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends Robot {

    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }
    
    @Override
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends Robot {

    public WheelRobot(RobotPool pool) {
        super(pool);
    }
    
    @Override
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class RobotPool {
    private Set<Robot> pool = new HashSet<>();
    
    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }
    
    public synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
        for (Robot r : pool) {
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
            wait();
            hire(robotType, d);    // 递归调用
        }
    }
    
    public synchronized void release(Robot r) {
        add(r);
    }
}

public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        CarQueue chassisQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
