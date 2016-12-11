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

package org.mingle.orange.arithmetic.util;

import org.junit.Test;
import org.mingle.orange.arithmetic.util.MaxValueInQueue.Stack;
import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
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
