package org.orange.features.java16;

/**
 * @author mingle
 */
public class InstanceofChanges {
    public static void main(String[] args) {
        Object obj = "string";
        if (obj instanceof String s) {
            System.out.println(s);
        }
    }
}
