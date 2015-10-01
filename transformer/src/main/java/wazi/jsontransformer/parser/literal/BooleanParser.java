package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.LiteralExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.ExpressionParser;
import wazi.jsontransformer.parser.exception.UnexpectedCharacterException;

public class BooleanParser implements ExpressionParser {

	public BaseExpression readExpression(JTEX jtex) {
		if (jtex.retrieveNext() == 't') {
			return readTrueValue(jtex);
		} else if (jtex.retrieveNext() == 'f') {
			return readFalseValue(jtex);
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading bool value.");
		}
	}

	private BaseExpression readFalseValue(JTEX jtex) {
		if (jtex.next() == 'f' &&
						jtex.next() == 'a' &&
						jtex.next() == 'l' &&
						jtex.next() == 's' &&
						jtex.next() == 'e') {
			return new LiteralExpression(false, jtex.getNextPosition() - 5, jtex.getNextPosition() - 1);//"false" contains 5 character
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}

	private BaseExpression readTrueValue(JTEX jtex) {
		if (jtex.next() == 't' &&
						jtex.next() == 'r' &&
						jtex.next() == 'u' &&
						jtex.next() == 'e') {
			return new LiteralExpression(true, jtex.getNextPosition() - 4, jtex.getNextPosition() - 1);//"true" contains 5 character
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}
}
