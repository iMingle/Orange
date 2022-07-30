package org.orange.features.java12;

import org.jooq.tools.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mingle
 */
public class StringChanges {
    public static void main(String[] args) {
        String result = "foo\nbar\nbar2".indent(4);
        System.out.println(result);

        List<String> names = List.of(
                "   Alex",
                "brian");

        List<String> transformedNames = new ArrayList<>();

        for (String name : names) {
            String transformedName = name.transform(String::strip)
                    .transform(StringUtils::toCamelCase);
            transformedNames.add(transformedName);
        }

        System.out.println(transformedNames);
    }
}
