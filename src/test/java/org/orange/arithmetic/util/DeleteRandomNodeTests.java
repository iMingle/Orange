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

package org.orange.arithmetic.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
 * @author mingle
 */
public class DeleteRandomNodeTests {
    @Test
    public void deleteRandomNode() {
        DeleteRandomNode.Node<String> head = new DeleteRandomNode.Node<>();
        head.data = "HEAD";
        DeleteRandomNode.Node<String> current = new DeleteRandomNode.Node<>();
        current.data = "CURRENT";
        head.next = current;
        DeleteRandomNode.Node<String> next = new DeleteRandomNode.Node<>();
        next.data = "NEXT";
        current.next = next;
        DeleteRandomNode.Node<String> tail = new DeleteRandomNode.Node<>();
        tail.data = "TAIL";
        next.next = tail;
        tail.next = null;
        
        StringBuilder builder = new StringBuilder();
        DeleteRandomNode.Node<String> tempHead = head;
        
        do {
            builder.append(head.data).append("->");
        } while ((head = head.next) != null);
        
        assertThat("HEAD->CURRENT->NEXT->TAIL->".equals(builder.toString())).isTrue();
        builder.delete(0, builder.length());
        
        DeleteRandomNode.deleteRandomNode(current);
        
        do {
            builder.append(tempHead.data).append("->");
        } while ((tempHead = tempHead.next) != null);
        
        assertThat("HEAD->NEXT->TAIL->".equals(builder.toString())).isTrue();
    }
}
