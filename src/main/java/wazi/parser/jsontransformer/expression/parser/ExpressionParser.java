package wazi.parser.jsontransformer.expression.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wazi.parser.jsontransformer.expression.function.LiteralValueParser;

/**
 * Expression evaluation class
 * @author wazi
 *
 */
public class ExpressionParser {

	/**
	 * Regex: /\w+\.\w+\(.*\)/ <br>
	 * Match example: "f.s ( 1 ,  3 , "1a2")"
	 */
	static final Pattern PATTERN_FUNCTION = Pattern.compile("\\w+\\.\\w+\\(.*\\)");
	static final Pattern PATTERN_PARAM = Pattern.compile("\\w+\\.\\w+\\(.*\\)");

	private JsonPathParser jsonPath;

	public ExpressionParser(String originalJson) {

		jsonPath = new JsonPathParser(originalJson);
	}

	/**
	 * Evaluate transforming expression
	 * 
	 * @param exp
	 *            JSON transforming expression. Ex: D.add(J.s($.temp), 15) to
	 *            extract temp value from {temp: 11} then add 15
	 * @return result after evaluation
	 */
	public Object eval(String jtex) {

		if (jtex.startsWith("$")) {
			return jsonPath.select(jtex);

		}

		Matcher funcMatcher = PATTERN_FUNCTION.matcher(jtex.trim());

		if (funcMatcher.matches()) {

			FunctionCall fc = extractFunctionCall(funcMatcher);
			return evalFunction(jtex);

		} else {
			return LiteralValueParser.parseLiteral(jtex);
		}
	}

	FunctionCall extractFunctionCall(Matcher funcMatcher) {

		int groupCount = funcMatcher.groupCount();
		return null;
	}

	Object evalFunction(String jtexFunction) {

		return null;
	}

	class FunctionCall {

		String className;
		String methodNameString;
		List<String> args;
	}
}
