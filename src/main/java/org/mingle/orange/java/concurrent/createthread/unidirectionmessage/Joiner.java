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
 * 组合器
 * 
 * @author mingle
 */
public abstract class Joiner extends SingleOutputPushStage implements
        DualInputPushStage {
    protected Box a = null; // incoming from putA
    protected Box b = null; // incoming from putB

    protected abstract Box join(Box p, Box q);

    protected synchronized Box joinFromA(Box p) {
        while (a != null)
            // wait until last consumed
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        a = p;
        return tryJoin();
    }

    protected synchronized Box joinFromB(Box p) { // symmetrical
        while (b != null)
            try {
                wait();
            } catch (InterruptedException ie) {
                return null;
            }
        b = p;
        return tryJoin();
    }

    protected synchronized Box tryJoin() {
        if (a == null || b == null)
            return null; // cannot join
        Box joined = join(a, b); // make combined box
        a = b = null; // forget old boxes
        notifyAll(); // allow new puts
        return joined;
    }

    @Override
    public void putA(Box p) {
        Box j = joinFromA(p);
        if (j != null)
            next1().putA(j);
    }

    @Override
    public void putB(Box p) {
        Box j = joinFromB(p);
        if (j != null)
            next1().putA(j);
    }
}

class HorizontalJoiner extends Joiner {
    protected Box join(Box p, Box q) {
        return new HorizontallyJoinedPair(p, q);
    }
}

class VerticalJoiner extends Joiner {
    protected Box join(Box p, Box q) {
        return new VerticallyJoinedPair(p, q);
    }
}
