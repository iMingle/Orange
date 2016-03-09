/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.junit.Test;
import org.mingle.orange.arithmetic.util.MaxValueInQueue.Stack;
import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class MaxValueInQueueTests {
    @Test
    public void max() {
        Stack<Integer> stackA = new Stack<>(10);
        Stack<Integer> stackB = new Stack<>(10);
        stackA.push(2);
        stackA.push(8);
        stackA.push(5);
        stackA.push(4);
        stackB.push(1);
        stackB.push(9);
        stackB.push(3);
        stackB.push(6);
        stackB.push(10);
        
        MaxValueInQueue<Integer> queue = new MaxValueInQueue<>(stackA, stackB);
        assertThat(queue.max() == 10).isTrue();
    }
}
