/**
 * Copyright (c) 2014, Mingle. All rights reserved.
 */
package org.mingle.orange.enumeration;

/**
 * @version 1.0 2014��7��8��
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 *
 */
public enum EnumTest {
	RED("��ɫ", 1), GREEN("��ɫ", 2), BLANK("��ɫ", 3), YELLO("��ɫ", 4);
	
	// ��Ա����
    private String name;
    private int index;

    private EnumTest() {
    	
    }
    
	// ���췽��
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
