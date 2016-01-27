/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Map;

/**
 * 用EnumMap代替序数索引
 * 
 * @since 1.8
 * @author Mingle
 */
public class EnumMap {
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
            
            private static final Map<Phase, Map<Phase, Transition>> m = new java.util.EnumMap<>(Phase.class);
            static {
                for (Phase p : Phase.values())
                    m.put(p, new java.util.EnumMap<>(Phase.class));
                for (Transition trans : Transition.values())
                    m.get(trans.src).put(trans.dst, trans);
            }
            
            public static Transition from(Phase src, Phase dst) {
                return m.get(src).get(dst);
            }
        }
    }
    
}
