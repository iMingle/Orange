/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations.atunit;

import org.mingle.orange.java.util.OSExecute;

/**
 * Applying @Unit to generics.
 *
 * @since 1.8
 * @author Mingle
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
