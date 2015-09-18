package wazi.jsontransformer.expression.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;

public class BooleanParser {

	public boolean readBool(JTEX jtex) {
		if (jtex.retrieveNext() == 't') {
			return readTrueValue(jtex);
		} else if (jtex.retrieveNext() == 'f') {
			return readFalseValue(jtex);
		} else {
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.retrieveNext(), "Exception while reading bool value.");
		}

	}

	private boolean readFalseValue(JTEX jtex) {
		//@formatter:off
		if (	jtex.next() == 'f' && 
				jtex.next() == 'a' && 
				jtex.next() == 'l' && 
				jtex.next() == 's' && 
				jtex.next() == 'e') {
		// @formatter:on
			return false;
		} else {
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}

	private boolean readTrueValue(JTEX jtex) {
		//@formatter:off
		if (	jtex.next() == 't' && 
				jtex.next() == 'r' && 
				jtex.next() == 'u' && 
				jtex.next() == 'e') {
		//@formatter:on
			return true;
		} else {
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Exception while reading bool value.");
		}
	}
}