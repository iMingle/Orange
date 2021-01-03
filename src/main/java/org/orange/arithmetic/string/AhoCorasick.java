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

package org.orange.arithmetic.string;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 多模式串匹配算法：AC 自动机
 *
 * @author mingle
 */
public class AhoCorasick {
    private static final int R = 256;        // ASCII

    private final AcNode root = new AcNode('/');

    private static class AcNode {
        public char data;
        public AcNode[] children = new AcNode[R];
        public boolean isEndingChar = false; // 结尾字符为true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度
        public AcNode fail; // 失败指针

        public AcNode(char data) {
            this.data = data;
        }
    }

    public AhoCorasick(List<String> patterns) {
        for (String s : patterns) {
            insert(s);
        }

        buildFailurePointer();
    }

    // 往Trie树中插入一个字符串
    private void insert(String pattern) {
        AcNode p = root;
        for (int i = 0; i < pattern.length(); ++i) {
            int index = pattern.charAt(i);
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(pattern.charAt(i));
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.length = pattern.length();
    }

    private void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    public void match(String text) {
        int n = text.length();
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text.charAt(i);
            while (p != null && p.children[idx] == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }

            if (p != null) {
                p = p.children[idx];
            }

            if (p == null) p = root; // 如果没有匹配的，从root开始重新匹配
            AcNode tmp = p;
            while (tmp != null && tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar) {
                    int pos = i - tmp.length + 1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }

    public static void main(String[] args) {
        List<String> patterns = Lists.newArrayList("at", "spere", "image", "country");
        AhoCorasick ac = new AhoCorasick(patterns);
        String text = "Early in the day it was whispered that we should sail in a boat, only thou and I, and never a "
                + "soul in the world would know of this our pilgrimage to no country and to no end.";
        ac.match(text);
    }
}
