/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.structural.adapter;

import org.mingle.orange.designpattern.structural.Point;

/**
 * 形状,Shape假定有一个边框,这个边框由它相对的两角定义
 * 
 * @since 1.0
 * @author Mingle
 */
public interface Shape {
    void scope(Point bottomLeft, Point topRight);
    Manipulator createManipulator();
}
