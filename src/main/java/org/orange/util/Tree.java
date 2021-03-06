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

package org.orange.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 实现Tree结构
 *
 * @author mingle
 */
public class Tree {
    /**
     * 唯一标识一个树节点
     */
    private String id;
    /**
     * 树节点显示名称
     */
    private String text;
    /**
     * 父节点,#表示根节点
     */
    private String parent = "#";

    /**
     * 
     */
    public Tree() {
        super();
    }

    /**
     * @param id
     * @param text
     */
    public Tree(String id, String text) {
        this();
        this.id = id;
        this.text = text;
    }

    /**
     * @param id
     * @param text
     * @param parent
     */
    public Tree(String id, String text, String parent) {
        this(id, text);
        this.parent = parent;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
    
    public static List<Tree> generateTreeNode() {
        List<Tree> nodes = Lists.newArrayList();
        nodes.add(new Tree("root", "ROOT"));
        nodes.add(new Tree("LevelOne1", "rootChild1", "root"));
        nodes.add(new Tree("LevelOne2", "rootChild2", "root"));
        nodes.add(new Tree("LevelOne3", "rootChild3", "root"));
        nodes.add(new Tree("LevelTwo1", "LevelOne1Child1", "LevelOne1"));
        nodes.add(new Tree("LevelTwo2", "LevelOne1Child2", "LevelOne1"));
        nodes.add(new Tree("LevelThree1", "LevelTwo2Child1", "LevelTwo2"));
        nodes.add(new Tree("LevelTwo3", "LevelOne2Child1", "LevelOne2"));
        nodes.add(new Tree("LevelTwo4", "LevelOne2Child2", "LevelOne2"));
        
        return nodes;
    }
    
    public static Tree findRootNode(List<Tree> nodes) {
        Tree tree = null;
        for (Tree t : nodes) {
            if (t.getParent() == "#") {
                tree = t;
                break;
            }
        }
        
        return tree;
    }
    
    public static void findChildrenNode(List<Tree> nodes, Tree node) {
        for (Tree t : nodes) {
            if (t.getParent().equals(node.getId())) {
                System.out.println(t.getText());
                findChildrenNode(nodes, t);
            }
        }
    }

    public static void main(String[] args) {
        List<Tree> nodes = Tree.generateTreeNode();
        Tree root = Tree.findRootNode(nodes);
        System.out.println(root.getText());
        Tree.findChildrenNode(nodes, root);
    }
}
