package org.orange.features.java13;

/**
 * @author mingle
 */
public class SwitchChanges {
    public static void main(String[] args) {
        var me = 4;
        var operation = "平方";
        var result = switch (operation) {
            case "加倍" -> {
                yield me * 2;
            }
            case "平方" -> {
                yield me * me;
            }
            default -> me;
        };

        System.out.println(result);
    }
}
