package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.SymbolExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.exception.UnexpectedCharacterException;

/**
 * Created by wazi on 2015-09-27 027.
 */
public class SymbolParser implements ExpressionParser {
	@Override
	public BaseExpression readExpression(JTEX jtex) {
		if (jtex.next() != '#') throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expect '#'.");
		StringBuilder builder = new StringBuilder("#");
		while (jtex.retrieveNext() != ' ' && jtex.retrieveNext() != '\t') {
			if (isValidSymbolName(jtex.retrieveNext())) {
				builder.append(jtex.next());
			} else {
				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Invalid character. Expected alphanumeric and underscore character.");
			}
		}
		if (builder.length() < 2) throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expect symbol name");
		return new SymbolExpression(builder.toString(), jtex.getNextPosition() - builder.length(), jtex.getNextPosition() - 1);
	}

	private boolean isValidSymbolName(char character) {
		return ('0' <= character && character <= '9') ||
				('A' <= character && character <= 'Z') ||
				('a' <= character && character <= 'z') ||
				(character == '_');
	}
}
