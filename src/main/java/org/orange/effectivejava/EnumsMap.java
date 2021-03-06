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

package org.orange.effectivejava;

import java.util.EnumMap;
import java.util.Map;

/**
 * 用EnumMap代替序数索引
 *
 * @author mingle
 */
public class EnumsMap {
    enum Phase {
        SOLID, LIQUID, GAS;

        enum Transition {
            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID);

            private final Phase src;
            private final Phase dst;

            Transition(Phase src, Phase dst) {
                this.src = src;
                this.dst = dst;
            }

            private static final Map<Phase, Map<Phase, Transition>> m = new EnumMap<>(Phase.class);

            static {
                for (Phase p : Phase.values())
                    m.put(p, new EnumMap<>(Phase.class));
                for (Transition trans : Transition.values())
                    m.get(trans.src).put(trans.dst, trans);
            }

            public static Transition from(Phase src, Phase dst) {
                return m.get(src).get(dst);
            }
        }
    }

    public static void main(String[] args) {
        EnumsMap.Phase.Transition.from(Phase.SOLID, Phase.LIQUID);
    }
}
