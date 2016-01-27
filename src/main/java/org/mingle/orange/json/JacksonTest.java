/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        
        // JsonParser.Feature for configuring parsing settings:

        // to allow C/C++ style comments in JSON (non-standard, disabled by default)
        // (note: with Jackson 2.5, there is also `mapper.enable(feature)` / `mapper.disable(feature)`)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // to allow (non-standard) unquoted field names in JSON:
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // to allow use of apostrophes (single quotes), non standard
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // JsonGenerator.Feature for configuring low-level JSON generation:

        // to force escaping of non-ASCII characters:
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        
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
        
        String[] arrays = new String[] {"One", "Two"};
        json = mapper.writeValueAsString(arrays);
        System.out.println("============== Array ==============");
        System.out.println(json);
        System.out.println(Arrays.toString(mapper.readValue(json, String[].class)));
        
        System.out.println("============== JsonNode ==============");
        JsonNode node = mapper.readTree(json);
        for (Iterator<JsonNode> it = node.iterator(); it.hasNext(); )
            System.out.println(it.next());
        
        System.out.println("============== Streaming parser, generator ==============");
        JsonFactory f = mapper.getFactory(); // may alternatively construct directly too

        // First: write simple JSON output
        File jsonFile = new File("test.json");
        JsonGenerator g = f.createGenerator(new FileOutputStream(jsonFile));
        g.writeStartObject();
        g.writeStringField("message", "Hello world!");
        g.writeEndObject();
        g.close();

        // Second: read file back
        JsonParser p = f.createParser(jsonFile);

        JsonToken t = p.nextToken(); // Should be JsonToken.START_OBJECT
        t = p.nextToken(); // JsonToken.FIELD_NAME
        if ((t != JsonToken.FIELD_NAME) || !"message".equals(p.getCurrentName())) {
           // handle error
        }
        t = p.nextToken();
        if (t != JsonToken.VALUE_STRING) {
           // similarly
        }
        String msg = p.getText();
        System.out.printf("My message to you is: %s!\n", msg);
        p.close();
        
        System.out.println("============== Conversions List<Integer>->int[] ==============");
        List<Integer> sourceList = Lists.newArrayList();
        sourceList.add(1);
        sourceList.add(2);
        int[] ints = mapper.convertValue(sourceList, int[].class);
        System.out.println(Arrays.toString(ints));
    }

}

@Data class JacksonObject {
    @JsonProperty("No")
    private int no;
    @JsonProperty("Name")
    private String name;
    @JsonIgnore
    private String ignore;
    
    /**
     * 在将JSON转为对象时必须有默认构造方法
     */
    public JacksonObject() {}
    
    /**
     * @param no
     * @param name
     */
    @JsonCreator    // constructor can be public, private, whatever, and property can be final
    public JacksonObject(@JsonProperty("No") int no, @JsonProperty("Name") String name) {
        this.no = no;
        this.name = name;
    }
    
}
