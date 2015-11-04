/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.junit.Test;
import org.mingle.orange.arithmetic.util.TraversalBTreeByLevel.Node;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class TraversalBTreeByLevelTests {
	@Test
	public void printNodeAtLevel() {
		Node<Character> root = new Node<>('a');
		root.left = new Node<Character>('b');
		root.right = new Node<Character>('c');
		root.left.left = new Node<Character>('d');
		
		TraversalBTreeByLevel.printNodeAtLevel(root, 0);
	}
}
