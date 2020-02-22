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

package org.orange.java.speciality.annotations.atunit;

import org.orange.java.util.OSExecute;

/**
 * Applying @Unit to generics.
 *
 * @author mingle
 */
public class StackLStringTest extends StackL<String> {
    @Test
    void _push() {
        push("one");
        assert top().equals("one");
        push("two");
        assert top().equals("two");
    }

    @Test
    void _pop() {
        push("one");
        push("two");
        assert pop().equals("two");
        assert pop().equals("one");
    }

    @Test
    void _top() {
        push("A");
        push("B");
        assert top().equals("B");
        assert top().equals("B");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit StackLStringTest");
    }
}
