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

package org.mingle.orange.java.concurrent.createthread.unidirectionmessage;

/**
 * 复制阶段
 *
 * @author mingle
 */
public class Cloner extends DualOutputPushStage implements PushStage {

    @Override
    public void putA(Box p) {
        final Box p2 = p.duplicate();
        next1().putA(p);
        new Thread(() -> {
            next2().putA(p2);
        }).start();
    }

}
