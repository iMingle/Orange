/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.enumeration;

/**
 * @version 1.0 2014年7月8日
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public enum EnumTest {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
	
	// 成员变量
    private String name;
    private int index;

    private EnumTest() {
    	
    }
    
	// 构造方法
    private EnumTest(String name, int index) {
        this.name = name;
        this.index = index;
    }
	
    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
}
