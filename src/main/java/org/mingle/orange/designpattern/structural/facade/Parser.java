/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

/**
 * 语法分析器
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Parser {
    /**
     * 用ProgramNodeBuilder,Parser类由Scanner生成的标识符构建一颗语法分析树
     * 
     * @param scanner
     * @param builder
     */
    void parse(Scanner scanner, ProgramNodeBuilder builder);
}
