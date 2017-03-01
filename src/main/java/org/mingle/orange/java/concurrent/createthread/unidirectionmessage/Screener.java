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
 * 筛选阶段
 *
 * @author mingle
 */
public class Screener extends DualOutputPushStage implements PushStage {

    protected final BoxPredicate predicate;

    public Screener(BoxPredicate p) {
        predicate = p;
    }

    @Override
    public void putA(final Box p) {
        if (predicate.test(p)) {
            new Thread(() -> {
                next1().putA(p);
            }).start();
        } else
            next2().putA(p);
    }
}

interface BoxPredicate {
    boolean test(Box p);
}

class MaxSizePredicate implements BoxPredicate {

    protected final int max; // max size to let through

    public MaxSizePredicate(int maximum) {
        max = maximum;
    }

    public boolean test(Box p) {
        return p.size().height <= max && p.size().width <= max;
    }
}
