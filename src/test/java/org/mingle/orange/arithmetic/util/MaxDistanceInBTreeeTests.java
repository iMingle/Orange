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
import org.mingle.orange.arithmetic.util.MaxDistanceInBTreee.Node;
import static org.assertj.core.api.Assertions.*;

/**
 * 
 * 
 * @author mingle
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
