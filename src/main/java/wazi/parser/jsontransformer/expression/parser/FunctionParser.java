package wazi.parser.jsontransformer.expression.parser;

import java.util.LinkedList;
import java.util.List;

import wazi.parser.jsontransformer.expression.Expression;
import wazi.parser.jsontransformer.expression.FunctionExpression;
import wazi.parser.jsontransformer.expression.JsonPathExpression;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

public class FunctionParser {

	private ExpressionParser exParser;

	public FunctionParser(ExpressionParser exParser) {
		this.exParser = exParser;
	}

	public FunctionExpression readFunction(JTEX jtex) {

		StringBuilder className = new StringBuilder(exParser.packagePrefixString);
		StringBuilder methodName = new StringBuilder();

		Character firstChar = jtex.next();
		int position = jtex.getPosition();
		if ('A' <= firstChar && firstChar <= 'Z') {
			className.append(firstChar);
		} else {
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Class name's first character must be in upper case.");
		}

		while (isValidJavaNameCharacter(jtex.retrieveNext())) {
			className.append(jtex.next());
		}

		if (jtex.next() != '.') throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Missing dot sign after class name.");

		while (isValidJavaNameCharacter(jtex.retrieveNext())) {
			methodName.append(jtex.next());
		}

		FunctionExpression funcEx;

		if ("J".equals(className.toString()) && "p".equals(methodName.toString())) {
			funcEx = new JsonPathExpression(position);
		} else {
			funcEx = new FunctionExpression(className.toString(), methodName.toString(), position);
		}

		//skip spaces
		while (jtex.retrieveNext() == ' ' || jtex.retrieveNext() == '\t') {
			jtex.next();
		}

		if (jtex.next() != '(') throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Missing parentheses after method name.");

		funcEx.setArguments(readArgumentList(jtex));

		if (jtex.retrieveNext() != ')')
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Missing parentheses after argument list.");

		return funcEx;
	}

	private List<Expression> readArgumentList(JTEX jtex) {

		List<Expression> args = new LinkedList<>();
		Expression arg = null;
		do {
			arg = readArgument(jtex);
			if (arg == null && args.size() == 0) {//empty argument list
				return null;
			}
			args.add(arg);
			if (jtex.retrieveNext() == ')') {
				break;
			}
			
		} while (arg != null);

		if (arg == null) {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(),
					"Exception while reading argument. Expect an expression.");
		}
		return args;
	}

	private Expression readArgument(JTEX jtex) {

		//skip spaces
		while (jtex.retrieveNext() == ' ' || jtex.retrieveNext() == '\t') {
			jtex.next();
		}

		if (jtex.retrieveNext() == ')') {//only valid in case of empty argument list
			return null;
		}

		Expression expression = exParser.readExpression(jtex);

		//skip spaces
		while (jtex.retrieveNext() == ' ' || jtex.retrieveNext() == '\t') {
			jtex.next();
		}

		if (jtex.retrieveNext() == ',') {//finish reading an argument
			jtex.next();//move to next argument
			return expression;
		} else if (jtex.retrieveNext() == ')') {//finish reading all arguments
			return expression;
		} else {
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Exception while reading argument");
		}
	}

	private boolean isValidJavaNameCharacter(char c) {

		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || (c == '_');
	}
}
