/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.facade;

import java.io.InputStream;

/**
 * 门面编译器
 * 
 * @since 1.0
 * @author Mingle
 */
public class Compiler {
    public void compile(InputStream input, BytecodeStream output) {
        Scanner scanner = new Scanner(input);
        ProgramNodeBuilder builder = new ProgramNodeBuilder();
        Parser parser = new Parser() {
            @Override public void parse(Scanner scanner, ProgramNodeBuilder builder) {
                
            }
        };
        
        parser.parse(scanner, builder);
        
        RISCCodeGenerator generator = new RISCCodeGenerator(output);
        ProgramNode parseTree = builder.rootNode();
        parseTree.traverse(generator);
    }
}
