package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.BooleanExpression;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

public class BooleanParser implements TokenParser<BooleanExpression> {

	public BooleanExpression read(JTEX jtex) {
		if (jtex.retrieveNext() == 't') {
			return readTrueValue(jtex);
		} else if (jtex.retrieveNext() == 'f') {
			return readFalseValue(jtex);
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading bool value.");
		}
	}

	private BooleanExpression readFalseValue(JTEX jtex) {
		if (jtex.next() == 'f' &&
						jtex.next() == 'a' &&
						jtex.next() == 'l' &&
						jtex.next() == 's' &&
						jtex.next() == 'e') {
			return new BooleanExpression(false, jtex.getNextPosition() - 5);//"false" contains 5 character
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}

	private BooleanExpression readTrueValue(JTEX jtex) {
		if (jtex.next() == 't' &&
						jtex.next() == 'r' &&
						jtex.next() == 'u' &&
						jtex.next() == 'e') {
			return new BooleanExpression(true, jtex.getNextPosition() - 4);//"true" contains 5 character
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}
}
