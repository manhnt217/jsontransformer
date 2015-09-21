package wazi.jsontransformer.expression.function;


/**
 * Integer functions
 */
public class I {

	public static int add(int... args) {

		int sum = 0;
		for (int i : args) {
			sum += i;
		}
		return sum;
	}
	
	public static String dummy(String xml, Double... arg) {
		StringBuilder builder = new StringBuilder(xml);
		double sum = 0;
		for (Double d : arg) {
			sum += d;
		}
		builder.append(sum);
		return builder.toString();
	}
	
	public static int div(int a, int b) {

		return a / b;
	}
	
	public static Boolean gt(int a, int b) {
		return a > b;
	}
	
	public static Boolean lt(int a, int b) {
		return a < b;
	}
	
	public static Boolean gte(int a, int b) {
		return a >= b;
	}
	
	public static Boolean lte(int a, int b) {
		return a <= b;
	}
}