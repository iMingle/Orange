/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mingle.orange.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * json测试
 *
 * @author mingle
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

        String json;
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

        String[] arrays = new String[]{"One", "Two"};
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
