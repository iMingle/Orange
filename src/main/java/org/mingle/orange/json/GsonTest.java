/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.json;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import lombok.ToString;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Gson测试
 * 
 * @since 1.8
 * @author Mingle
 */
public class GsonTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println("============== Primitives ==============");
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(new Long(10)));
        int[] values = { 1 };
        System.out.println(gson.toJson(values));
        
        int one = gson.fromJson("1", int.class);
        System.out.println(one);
        Integer ione = gson.fromJson("1", Integer.class);
        System.out.println(ione);
        Long lone = gson.fromJson("1", Long.class);
        System.out.println(lone);
        Boolean fal = gson.fromJson("false", Boolean.class);
        System.out.println(fal);
        String str = gson.fromJson("\"abc\"", String.class);
        System.out.println(str);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
        System.out.println(Arrays.toString(anotherStr));
        
        System.out.println("============== Object ==============");
        GsonObject<String> obj = new GsonObject<String>(0, "Mingle", "X");
        String objGson = gson.toJson(obj);
        System.out.println(objGson);
        
        GsonObject<String> objTransfer = gson.fromJson(objGson, GsonObject.class);
        System.out.println(objTransfer);
        
        System.out.println("============== Array ==============");
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

        System.out.println(gson.toJson(ints));
        System.out.println(gson.toJson(strings));

        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
        System.out.println(Arrays.toString(ints2));
        
        System.out.println("============== Collections ==============");
        Collection<Integer> intss = Lists.asList(1, new Integer[] {2,3,4,5});

        String json = gson.toJson(intss);
        System.out.println(json);

        Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
        Collection<Integer> ints22 = gson.fromJson(json, collectionType);
        System.out.println(ints22);
        
        @SuppressWarnings("rawtypes")
        Collection collection = Lists.newArrayList();
        collection.add("hello");
        collection.add(5);
        json = gson.toJson(collection);
        System.out.println(json);
        System.out.println("Using Gson.toJson() on a raw collection: " + json);
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();
        String message = gson.fromJson(array.get(0), String.class);
        int number = gson.fromJson(array.get(1), int.class);
        System.out.printf("Using Gson.fromJson() to get: %s, %d", message, number);
    }

}

@ToString
class GsonObject<T> {
    private int no;
    private String name;
    private T value;
    
    /**
     * @param no
     * @param name
     */
    public GsonObject(int no, String name, T value) {
        this.no = no;
        this.name = name;
        this.value = value;
    }

}