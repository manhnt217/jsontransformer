package wazi.jsontransformer.expression.function;

/**
 * String functions
 */
public class S {

    public static String concat(Object... args) {

        StringBuilder builder = new StringBuilder();
        for (Object object : args) {
            if(object == null) continue;
            builder.append(object.toString());
        }
        return builder.toString();
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
