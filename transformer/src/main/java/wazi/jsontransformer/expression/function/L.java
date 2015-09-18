package wazi.jsontransformer.expression.function;


import java.util.Arrays;
import java.util.List;

/**
 * List functions
 */
public class L {
	public static List<?> toList(Object... args) {
		return Arrays.asList(args);
	}
}
