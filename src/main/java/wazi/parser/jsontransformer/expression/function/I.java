package wazi.parser.jsontransformer.expression.function;


/**
 * Integer functions
 */
public class I {

	public static Integer add(Integer... args) {

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
	
	public static Integer div(Integer a, Integer b) {

		return a / b;
	}
}