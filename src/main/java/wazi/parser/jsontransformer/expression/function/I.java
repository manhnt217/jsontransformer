package wazi.parser.jsontransformer.expression.function;


/**
 * Integer functions
 */
public class I {

	public static int add(Integer... args) {

		int sum = 0;
		for (int i : args) {
			sum += i;
		}
		return sum;
	}
	
	public static String dummy(String abc, Object... args) {
		
		StringBuilder builder = new StringBuilder(abc);
		for (Object object : args) {
			builder.append(object.toString());
		}
		return builder.toString();
	}

	public static int div(int a, int b) {

		return a / b;
	}
}