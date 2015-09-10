package wazi.parser.jsontransformer.expression.function;

/**
 * String functions
 */
public class S {

    public static String concat(Object str1, Object str2) {
        return String.valueOf(str1) + String.valueOf(str2);
    }

    public static String upper(String str) {
        return str.toUpperCase();
    }

    public static String lower(String str) {
        return str.toLowerCase();
    }

    public static Boolean equal(String str1, String str2) {
        return str1.equals(str2);
    }
}