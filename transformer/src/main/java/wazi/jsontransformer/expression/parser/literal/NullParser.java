package wazi.jsontransformer.expression.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;

public class NullParser {

	public Object readNullValue(JTEX jtex) {
		//@formatter:off
		if (	jtex.next() == 'n' && 
				jtex.next() == 'u' && 
				jtex.next() == 'l' && 
				jtex.next() == 'l') {

			return null;// explicit null
			//@formatter:on
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading null value.");
		}
	}
}
