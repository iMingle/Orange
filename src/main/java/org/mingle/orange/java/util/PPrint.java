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

package org.mingle.orange.java.util;

import java.util.*;

/**
 * Pretty-printer for collections
 *
 * @author Mingle
 */
public class PPrint {
    public static String pformat(Collection<?> c) {
        if (c.size() == 0)
            return "[]";
        StringBuilder result = new StringBuilder("[");
        for (Object elem : c) {
            if (c.size() != 1)
                result.append("\n  ");
            result.append(elem);
        }
        if (c.size() != 1)
            result.append("\n");
        result.append("]");
        return result.toString();
    }

    public static void pprint(Collection<?> c) {
        System.out.println(pformat(c));
    }

    public static void pprint(Object[] c) {
        System.out.println(pformat(Arrays.asList(c)));
    }
} // /:~
