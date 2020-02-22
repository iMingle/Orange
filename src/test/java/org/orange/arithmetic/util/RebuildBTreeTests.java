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
public class RebuildBTreeTests {
    @Test
    public void rebuild() {
        RebuildBTree<Character> bTree = new RebuildBTree<>();
        String preorder = "abdcef";
        String inorder = "dbaecf";
        RebuildBTree.Node<Character> root = new RebuildBTree.Node<>();
        root.data = preorder.charAt(0);
        bTree.rebuild(preorder, inorder, 6, root);
        
        assertThat(root.left.data).isEqualTo('b');
        assertThat(root.left.left.data).isEqualTo('d');
        assertThat(root.right.data).isEqualTo('c');
        assertThat(root.right.left.data).isEqualTo('e');
        assertThat(root.right.right.data).isEqualTo('f');
    }
}
