/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import lombok.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * json测试
 * 
 * @since 1.8
 * @author Mingle
 */
public class JacksonTest {
	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JacksonObject jsonObj = null;
		String json = "";
		try {
			jsonObj = new JacksonObject(8, "Mingle");
			json = mapper.writeValueAsString(jsonObj);
			System.out.println("============== Bean ==============");
			System.out.println(json);
			System.out.println(mapper.readValue(json, JacksonObject.class).toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> lists = Lists.newArrayList();
		lists.add("One");
		lists.add("Two");
		json = mapper.writeValueAsString(lists);
		System.out.println("============== List ==============");
		System.out.println(json);
		System.out.println(mapper.readValue(json, List.class).toString());
		
		Map<Integer, String> maps = Maps.newHashMap();
		maps.put(1, "One");
		maps.put(2, "Two");
		json = mapper.writeValueAsString(maps);
		System.out.println("============== Map ==============");
		System.out.println(json);
		System.out.println(mapper.readValue(json, Map.class).toString());
	}

}

@Data class JacksonObject {
	private int no;
	private String name;
	
	/**
	 * 在将JSON转为对象时必须有默认构造方法
	 */
	public JacksonObject() {}
	
	/**
	 * @param no
	 * @param name
	 */
	public JacksonObject(int no, String name) {
		this.no = no;
		this.name = name;
	}
	
}
