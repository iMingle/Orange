package org.orange.features.java16;

/**
 * @author mingle
 */
public record Record(String name, Integer age) {
    public static void main(String[] args) {
        Record record = new Record("name", 1);
        System.out.println(record);
    }
}
