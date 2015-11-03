/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.junit.Test;
import org.mingle.orange.arithmetic.util.RebuildBTree.Node;
import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class RebuildBTreeTests {
	@Test
	public void rebuild() {
		RebuildBTree<Character> bTree = new RebuildBTree<>();
		String preorder = "abdcef";
		String inorder = "dbaecf";
		Node<Character> root = new Node<>();
		root.data = preorder.charAt(0);
		bTree.rebuild(preorder, inorder, 6, root);
		
		System.out.println(root);
	}
}
