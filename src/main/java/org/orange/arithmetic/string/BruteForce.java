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

package org.orange.arithmetic.string;

import java.util.Objects;

/**
 * 暴力匹配算法，也叫朴素匹配算法
 *
 * @author mingle
 */
public class BruteForce {

    public static int search(String source, String pattern) {
        if (Objects.isNull(source) || Objects.isNull(pattern)) {
            return -1;
        }

        if (pattern.length() == 0) {
            return 0;
        }

        char first = pattern.charAt(0);
        int max = source.length() - pattern.length();

        for (int i = 0; i <= max; i++) {
            /* Look for first character. */
            if (source.charAt(i) != first) {
                while (++i <= max && source.charAt(i) != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + pattern.length() - 1;
                for (int k = 1; j < end && source.charAt(j) == pattern.charAt(k); j++, k++) ;

                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }

        return -1;
    }
}
