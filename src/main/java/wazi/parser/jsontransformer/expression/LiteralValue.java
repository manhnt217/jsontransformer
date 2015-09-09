package wazi.parser.jsontransformer.expression;

public class LiteralValue {
	public static Object parseLiteral(String literal) {

		if (literal == null || literal.length() == 0) {
			throw new IllegalArgumentException("Empty input");

		} else if (literal.startsWith("\"") && literal.endsWith("\"")) {//String literal
			return literal.substring(1, literal.length() - 1);

		} else if ("true".equalsIgnoreCase(literal)) {
			return true;

		} else if ("false".equalsIgnoreCase(literal)) {
			return false;

		} else if ("null".equalsIgnoreCase(literal)) {
			return null;

		} else {
			try {
				//try with integer first
				return Integer.parseInt(literal);
			} catch (NumberFormatException ex) {

				try {
					return Double.parseDouble(literal);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Invalid input: " + literal);
				}
			}
		}
	}
}
