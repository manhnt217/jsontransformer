package wazi.jsontransformer.expression.parser;

import wazi.jsontransformer.expression.Expression;
import wazi.jsontransformer.expression.FunctionExpression;
import wazi.jsontransformer.expression.JsonPathExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;

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

		if ((exParser.packagePrefixString + "J").equals(className.toString()) && "p".equals(methodName.toString())) {
			funcEx = new JsonPathExpression(position);
			((JsonPathExpression)funcEx).setInputJson(exParser.getInputJSON());
		} else {
			funcEx = new FunctionExpression(className.toString(), methodName.toString(), position);
		}

		jtex.skipBlank();

		readArgumentList(jtex, funcEx);

		return funcEx;
	}

	private void readArgumentList(JTEX jtex, FunctionExpression funcEx) {

		if (jtex.next() != '(') throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Missing parentheses after method name.");

		Expression arg = null;

		//read arguments
		while (true) {

			jtex.skipBlank();

			if (jtex.retrieveNext() == ')') {//only valid in case of empty argument list
				if (funcEx.getArguments().size() == 0) return; //empty argument list
				else {
					throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Exception while reading argument");
				}
			} else {

				arg = exParser.readExpression(jtex);
				funcEx.addArgument(arg);

				jtex.skipBlank();

				if (jtex.retrieveNext() == ',') {//normal case, finish reading an argument
					jtex.next();//move to next argument
					continue;
				} else if (jtex.retrieveNext() == ')') {//finish reading all arguments
					//read ')' and break to close argument list and return
					jtex.next();
					break;
				} else {
					throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Exception while reading argument");
				}
			}
		}
	}

	private boolean isValidJavaNameCharacter(char c) {

		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || (c == '_');
	}
}
