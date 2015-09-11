package wazi.parser.jsontransformer.expression.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wazi.parser.jsontransformer.expression.BaseExpression;
import wazi.parser.jsontransformer.expression.Expression;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

/**
 * Expression evaluation class
 * 
 * @author wazi
 *
 */
public class ExpressionParser {

	/**
	 * Regex: /\w+\.\w+\(.*\)/ <br>
	 * Match example: "f.s ( 1 ,  3 , "1a2")"
	 */
	static final Pattern PATTERN_FUNCTION = Pattern.compile("\\w+\\.\\w+\\(.*\\)");
	/**
	 * String literal pattern: "((\\[tbnrf'"\\])|[^\\]|(\\u[a-fA-F0-9]{4}))*?"
	 * (include 2 double-quotes at start and end) <br>
	 * String = "<valid charaters>" <br>
	 * Valid charater = \t, \b, \n, \r, \f, \', \\ <br>
	 * OR not \ character <br>
	 * OR \ u following by 4 hexadecimal characters
	 */
	static final Pattern PATTERN_STRING_LITERAL = Pattern.compile("\"((\\\\[tbnrf'\"\\\\])|[^\\\\]|(\\\\u[a-fA-F0-9]{4}))*?\"");
	static final Pattern PATTERN_NUMBER_LITERAL = Pattern.compile("");

	private FunctionParser functionParser;
	private StringParser stringParser;
	private NumberParser numberParser;
	private BooleanParser booleanParser;
	private NullParser nullParser;

	public ExpressionParser() {

		functionParser = new FunctionParser(this);
		stringParser = new StringParser();
		numberParser = new NumberParser();
		booleanParser = new BooleanParser();
		nullParser = new NullParser();
	}

	public Expression readExpression(JTEX jtex) {

		switch (jtex.retrieveNext()) {
			case '"': return new BaseExpression(stringParser.readString(jtex), jtex.getNextPosition());
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '+':
			case '-': return new BaseExpression(numberParser.readNumber(jtex), jtex.getNextPosition());
			case 't':
			case 'f': return new BaseExpression(booleanParser.readBool(jtex), jtex.getNextPosition());
			case 'n': return new BaseExpression(nullParser.readNullValue(jtex), jtex.getNextPosition());
			default: {
				if('A' <= jtex.retrieveNext() && jtex.retrieveNext() <= 'Z') return functionParser.readFunction(jtex);
				else throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading expression.");
			}
		}
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

//		if (jtex.startsWith("$")) {
//			return jsonPath.select(jtex);
//
//		}

		Matcher funcMatcher = PATTERN_FUNCTION.matcher(jtex.trim());

		if (funcMatcher.matches()) {

			FunctionCall fc = extractFunctionCall(funcMatcher);
			return evalFunction(jtex);

		} else {
			return null;
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
