package wazi.jsontransformer.parser;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.FunctionExpression;
import wazi.jsontransformer.expression.SymbolLiteral;
import wazi.jsontransformer.expression.helper.function.Functions;
import wazi.jsontransformer.expression.jtex.JTEX;

import java.util.LinkedList;
import java.util.List;

public class FunctionExpressionParser extends BaseExpressionParser<FunctionExpression> {

	@Override
	public FunctionExpression read0(JTEX jtex) {

		jtex.skipBlank();

		FunctionExpression functionExpression = new FunctionExpression();
		functionExpression.setStart(jtex.getNextPosition());

		//If first character is upper case, we read a class name
		if ('A' <= jtex.retrieveNext() && jtex.retrieveNext() <= 'Z') {
			functionExpression.setClassName(readClassName(jtex));
		} else {
			functionExpression.setClassName(FunctionExpression.DEFAULT_FUNCTION_CLASS);
		}

		functionExpression.setMethodName(readMethodName(jtex));

		jtex.skipBlank();

		functionExpression.setArguments(readArgumentList(jtex));

		if (Functions.INPUT_JSON_FUNCTIONS.contains(functionExpression.getClassName() + "." + functionExpression.getMethodName())) {
			functionExpression.addArgument(new SymbolLiteral(FunctionExpression.SRC_JSON_SYMBOL, -1, -1));
		}

		functionExpression.setEnd(jtex.getNextPosition() - 1);
		return functionExpression;
	}

	private String readClassName(JTEX jtex) {

		StringBuilder className = new StringBuilder(FunctionExpression.DEFAULT_FUNCTION_PACKAGE);
		while (isValidJavaNameCharacter(jtex.retrieveNext())) {
			className.append(jtex.next());
		}

		if (jtex.next() != '.') {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Missing dot sign after class name.");
		}
		return className.toString();
	}

	private String readMethodName(JTEX jtex) {
		StringBuilder methodName = new StringBuilder();
		while (isValidJavaNameCharacter(jtex.retrieveNext())) {
			methodName.append(jtex.next());
		}
		if (methodName.length() == 0) throw new ParserException(jtex.getNextPosition(), "Method name cannot be empty");
		return methodName.toString();
	}

	private List<BaseExpression> readArgumentList(JTEX jtex) {

		List<BaseExpression> argList = new LinkedList<>();

		if (jtex.next() != '(')
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Missing parentheses after method name.");

		BaseExpression arg;

		//read arguments
		while (true) {

			jtex.skipBlank();

			if (jtex.retrieveNext() == ')') {//only valid in case of empty argument list
				if (argList.size() == 0) return argList; //empty argument list
				else {
					throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading argument");
				}
			} else {

				arg = expressionParser.read(jtex);
				argList.add(arg);

				jtex.skipBlank();

				if (jtex.retrieveNext() == ',') {//normal case, finish reading an argument
					jtex.next();//move to next argument
					continue;
				} else if (jtex.retrieveNext() == ')') {//finish reading all arguments
					//read ')' and break to close argument list and return
					jtex.next();
					break;
				} else {
					throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading argument");
				}
			}
		}

		return argList;
	}

	private boolean isValidJavaNameCharacter(char c) {

		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || (c == '_');
	}
}
