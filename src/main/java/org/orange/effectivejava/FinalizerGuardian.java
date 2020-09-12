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

/**
 * 终结方法守卫者
 *
 * 公有类FinalizerGuardian并没有终结方法,所以子类的终结方法是否调用super.finalize并不重要.
 * 对于每一个带有终结方法的非final公有类,都应该考虑使用这种方法.
 *
 * @author mingle
 */
public class FinalizerGuardian {
    private final Object finalizerGuardian = new Object() {
        @Override protected void finalize() throws Throwable {
            FinalizerGuardian.this.finalize();
        }
    };
}
