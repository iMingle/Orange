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

package org.mingle.orange.java7;

/**
 * 编译器在编译时先做处理：
 * ①case只有一种情况，直接转成if；
 * ②如果只有一个case和default，则直接转换为if...else...；
 * ③有多个case，先将String转换为hashCode，然后对应的进行处理，JavaCode在底层兼容Java7以前版本。
 * 
 * @author mingle
 */
public class SwitchString {
    public static void switchString(String str) {
        switch (str) {
        case "a":
            break;
        default:
            break;
        }
    }
}
