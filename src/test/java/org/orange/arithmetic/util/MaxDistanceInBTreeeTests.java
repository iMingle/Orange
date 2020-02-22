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
public class MaxDistanceInBTreeeTests {
    @Test
    public void findMaxLength() {
        MaxDistanceInBTreee<String> bTree = new MaxDistanceInBTreee<>();
        MaxDistanceInBTreee.Node<String> root = new MaxDistanceInBTreee.Node<>();
        root.data = "ROOT";
        MaxDistanceInBTreee.Node<String> rootLeft = new MaxDistanceInBTreee.Node<>();
        rootLeft.data = "ROOT_LEFT";
        MaxDistanceInBTreee.Node<String> rootRight = new MaxDistanceInBTreee.Node<>();
        rootRight.data = "ROOT_RIGHT";
        root.left = rootLeft;
        root.right = rootRight;
        MaxDistanceInBTreee.Node<String> rootLeftLeft = new MaxDistanceInBTreee.Node<>();
        rootLeft.data = "ROOT_LEFT_LEFT";
        rootLeft.left = rootLeftLeft;
        MaxDistanceInBTreee.Node<String> rootLeftRight = new MaxDistanceInBTreee.Node<>();
        rootLeftRight.data = "ROOT_LEFT_RIGHT";
        rootLeft.right = rootLeftRight;
        MaxDistanceInBTreee.Node<String> rootLeftRightRight = new MaxDistanceInBTreee.Node<>();
        rootLeftRightRight.data = "ROOT_LEFT_RIGHT_RIGHT";
        rootLeftRight.right = rootLeftRightRight;
        
        bTree.findMaxLength(root);
        assertThat(4 == bTree.maxLength).isTrue();
    }
}
