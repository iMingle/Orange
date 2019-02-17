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

package org.mingle.orange.util.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.jstack;
import static com.sun.btrace.BTraceUtils.println;

/**
 * @author mingle
 */
@BTrace
public class BtraceProd {
    @OnMethod(clazz = "org.mingle.pear.web.AsyncController", method = "sync",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    public static void m(@Self Object self, @ProbeMethodName String probeMethod,
                         @TargetMethodOrField String targetMethodOrField) {
        println(self);
        println(probeMethod + "." + targetMethodOrField);
    }

    /**
     * 方法耗时
     *
     * @param probeClass
     * @param duration
     */
    @OnMethod(clazz = "org.mingle.pear.web.AsyncController", method = "sync",
            location = @Location(Kind.RETURN))
    public static void m1(@ProbeClassName String probeClass, @Duration long duration) {
        println(probeClass + ".sync duration(ms): " + duration / 1000000);
    }

    /**
     * gc调用的地方
     */
    @OnMethod(clazz = "java.lang.System", method = "gc")
    public static void onSystemGC() {
        println("entered System.gc()");

        jstack();
    }

    /**
     * 当成程序退出时，执行一些命令
     *
     * @param code
     */
    @OnExit
    public static void onexit(int code) {
        println("BTrace program exits! with code: " + code);
    }

    /**
     * 可以用来统计调用次数
     */
    @Export
    public static long counter;

    @OnMethod(clazz = "com.test.BtraceTest", method = "add", location = @Location(value = Kind.RETURN))
    public static void m(@Self Object self, int a, int b, @Return int result, @Duration long time) {
        println("参数a: " + a + " b: " + b);
        println("花费时间: " + time * 1.0 / 1000 + "ms");
        jstack(); // 打印堆栈信息
        counter++;
    }

    /**
     * 通过事件触发，打印当前的程序调用次数
     */
    @OnEvent("1")
    public static void setL1() {
        BTraceUtils.println("executor count: " + counter);
    }

    /**
     * 监控程序是否走到第22行代码
     */
    @OnMethod(clazz = "com.test.BtraceTest", location = @Location(value = Kind.LINE, line = 22))
    public static void onBind() {
        println("执行到第22行");
    }

    /**
     * 每隔指定时间打印一下调用次数
     */
    @OnTimer(5000)
    public static void run() {
        BTraceUtils.println("executor count: " + counter);
    }
}
