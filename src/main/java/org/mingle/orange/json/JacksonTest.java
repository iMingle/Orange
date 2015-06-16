/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json测试
 * 
 * @since 1.8
 * @author Mingle
 */
public class JacksonTest {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JacksonObject jsonObj = new JacksonObject(8, "Mingle");
			String json = mapper.writeValueAsString(jsonObj);
			System.out.println(json);
			
			System.out.println(mapper.readValue(json, JacksonObject.class).toString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class JacksonObject {
	private int no;
	private String name;
	
	/**
	 * 在将JSON转为对象时必须有默认构造方法
	 */
	public JacksonObject() {}
	
	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param no
	 * @param name
	 */
	public JacksonObject(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + no;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JacksonObject other = (JacksonObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (no != other.no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JacksonObject [no=" + no + ", name=" + name + "]";
	}
	
}
