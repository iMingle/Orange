/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.junit.Test;
import org.mingle.orange.arithmetic.util.DeleteRandomNode.Node;
import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class DeleteRandomNodeTests {
	@Test
	public void deleteRandomNode() {
		Node<String> head = new Node<>();
		head.data = "HEAD";
		Node<String> current = new Node<>();
		current.data = "CURRENT";
		head.next = current;
		Node<String> next = new Node<>();
		next.data = "NEXT";
		current.next = next;
		Node<String> tail = new Node<>();
		tail.data = "TAIL";
		next.next = tail;
		tail.next = null;
		
		StringBuilder builder = new StringBuilder();
		Node<String> tempHead = head;
		
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
