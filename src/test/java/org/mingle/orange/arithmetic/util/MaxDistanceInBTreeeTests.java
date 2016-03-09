/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.arithmetic.util;

import org.junit.Test;
import org.mingle.orange.arithmetic.util.MaxDistanceInBTreee.Node;
import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class MaxDistanceInBTreeeTests {
    @Test
    public void findMaxLength() {
        MaxDistanceInBTreee<String> bTree = new MaxDistanceInBTreee<>();
        Node<String> root = new Node<>();
        root.data = "ROOT";
        Node<String> rootLeft = new Node<>();
        rootLeft.data = "ROOT_LEFT";
        Node<String> rootRight = new Node<>();
        rootRight.data = "ROOT_RIGHT";
        root.left = rootLeft;
        root.right = rootRight;
        Node<String> rootLeftLeft = new Node<>();
        rootLeft.data = "ROOT_LEFT_LEFT";
        rootLeft.left = rootLeftLeft;
        Node<String> rootLeftRight = new Node<>();
        rootLeftRight.data = "ROOT_LEFT_RIGHT";
        rootLeft.right = rootLeftRight;
        Node<String> rootLeftRightRight = new Node<>();
        rootLeftRightRight.data = "ROOT_LEFT_RIGHT_RIGHT";
        rootLeftRight.right = rootLeftRightRight;
        
        bTree.findMaxLength(root);
        assertThat(4 == bTree.maxLength).isTrue();
    }
}
