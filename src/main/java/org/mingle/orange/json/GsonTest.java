/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.json;

import lombok.ToString;

import com.google.gson.Gson;

/**
 * Gson测试
 * 
 * @since 1.8
 * @author Mingle
 */
public class GsonTest {

	public static void main(String[] args) {
		Gson gson = new Gson();
		GsonObject obj = new GsonObject(0, "Mingle");
		String objGson = gson.toJson(obj);
		System.out.println(objGson);
		
		GsonObject objTransfer = gson.fromJson(objGson, GsonObject.class);
		System.out.println(objTransfer);
	}

}

@ToString
class GsonObject {
	private int no;
	private String name;
	
	/**
	 * @param no
	 * @param name
	 */
	public GsonObject(int no, String name) {
		this.no = no;
		this.name = name;
	}

}