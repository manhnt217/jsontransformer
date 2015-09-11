package wazi.parser.jsontransformer.expression.parser;

import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

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
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Exception while reading null value.");
		}
	}
}
