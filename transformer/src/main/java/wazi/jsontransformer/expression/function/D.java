package wazi.jsontransformer.expression.function;

/**
 * Double (numeric) functions
 */
public class D {
    public static double add (double a, double b) {
        return a + b;
    }

    public static double div (double a, double b) {
        return a / b;
    }

    public static Boolean gt(double a, double b) {
        return a > b;
    }

    public static Boolean lt(double a, double b) {
        return a < b;
    }

    public static Boolean gte(double a, double b) {
        return a >= b;
    }

    public static Boolean lte(double a, double b) {
        return a <= b;
    }
}
