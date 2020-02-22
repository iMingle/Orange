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

/**
 * 
 * 
 * @author mingle
 */
public class TraversalBTreeByLevelTests {
    @Test
    public void printNodeAtLevel() {
        TraversalBTreeByLevel.Node<Character> root = new TraversalBTreeByLevel.Node<>('a');
        root.left = new TraversalBTreeByLevel.Node<Character>('b');
        root.right = new TraversalBTreeByLevel.Node<Character>('c');
        root.left.left = new TraversalBTreeByLevel.Node<Character>('d');
        root.left.right = new TraversalBTreeByLevel.Node<Character>('e');
        root.right.left = new TraversalBTreeByLevel.Node<Character>('f');
        root.right.right = new TraversalBTreeByLevel.Node<Character>('g');
        
        TraversalBTreeByLevel.printNodeAtLevel(root, 2);
        System.out.println();
        TraversalBTreeByLevel.printNodeByLevel(root);
        TraversalBTreeByLevel.printNodeByLevelBetter(root);
    }
}
