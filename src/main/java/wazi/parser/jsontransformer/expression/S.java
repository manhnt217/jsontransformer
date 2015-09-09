package wazi.parser.jsontransformer.expression;

/**
 * String functions
 */
public class S {

    public static String concat(String str1, String str2) {
        return str1 + str2;
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