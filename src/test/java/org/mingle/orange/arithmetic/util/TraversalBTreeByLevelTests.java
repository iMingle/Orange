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
import org.mingle.orange.arithmetic.util.TraversalBTreeByLevel.Node;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class TraversalBTreeByLevelTests {
    @Test
    public void printNodeAtLevel() {
        Node<Character> root = new Node<>('a');
        root.left = new Node<Character>('b');
        root.right = new Node<Character>('c');
        root.left.left = new Node<Character>('d');
        root.left.right = new Node<Character>('e');
        root.right.left = new Node<Character>('f');
        root.right.right = new Node<Character>('g');
        
        TraversalBTreeByLevel.printNodeAtLevel(root, 2);
        System.out.println();
        TraversalBTreeByLevel.printNodeByLevel(root);
        TraversalBTreeByLevel.printNodeByLevelBetter(root);
    }
}
