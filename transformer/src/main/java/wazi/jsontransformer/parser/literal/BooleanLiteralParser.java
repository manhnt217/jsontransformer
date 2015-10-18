package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.BooleanLiteral;
import wazi.jsontransformer.parser.BaseExpressionParser;

public class BooleanLiteralParser extends BaseExpressionParser<BooleanLiteral> {

	@Override
	public BooleanLiteral read0(JTEX jtex) {
		if (jtex.retrieveNext() == 't') {
			return readTrueValue(jtex);
		} else if (jtex.retrieveNext() == 'f') {
			return readFalseValue(jtex);
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading bool value.");
		}
	}

	private BooleanLiteral readFalseValue(JTEX jtex) {
		if (jtex.next() == 'f' &&
						jtex.next() == 'a' &&
						jtex.next() == 'l' &&
						jtex.next() == 's' &&
						jtex.next() == 'e') {
			return new BooleanLiteral(false, jtex.getNextPosition() - 5);//"false" contains 5 character
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}

	private BooleanLiteral readTrueValue(JTEX jtex) {
		if (jtex.next() == 't' &&
						jtex.next() == 'r' &&
						jtex.next() == 'u' &&
						jtex.next() == 'e') {
			return new BooleanLiteral(true, jtex.getNextPosition() - 4);//"true" contains 5 character
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}
}
