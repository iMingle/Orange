/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.adapter;

/**
 * 
 * 
 * @since 1.0
 * @author Mingle
 */
public class TextManipulator extends Manipulator {

    public TextManipulator(TextShape shape) {
        shape.createManipulator();
    }

}
