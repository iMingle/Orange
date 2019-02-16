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

package org.mingle.orange.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * @author mingle
 */
@BTrace
public class BtraceProd {
    @OnMethod(clazz = "org.mingle.pear.web.AsyncController", method = "sync",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    public static void m(@Self Object self, @ProbeMethodName String probeMethod,
                         @TargetMethodOrField String targetMethodOrField) {
//        BTraceUtils.println(self);
//        BTraceUtils.println(probeMethod + "." + targetMethodOrField);
    }

    @OnMethod(clazz = "org.mingle.pear.web.AsyncController", method = "sync",
            location = @Location(Kind.RETURN))
    public static void m1(@ProbeClassName String probeClass, @Duration long duration) {
        BTraceUtils.println(probeClass + ".sync duration(ms): " + duration / 1000000);
    }
}
