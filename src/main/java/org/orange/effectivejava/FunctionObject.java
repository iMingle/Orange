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

import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;

/**
 * 函数对象
 *
 * @author mingle
 */
public class FunctionObject {
    private static class StrLenCmp implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 3833101215017572079L;

        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }

    }

    public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StrLenCmp();

    public static void main(String[] args) {
        String[] stringArray = new String[]{"asda", "dsdas", "bsd"};
        Arrays.sort(stringArray, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Arrays.sort(stringArray, STRING_LENGTH_COMPARATOR);
    }
}
