package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NullLiteral;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

public class NullLiteralParser implements TokenParser<NullLiteral> {

	@Override
	public NullLiteral read(JTEX jtex) {
		if (jtex.next() == 'n' &&
				jtex.next() == 'u' &&
				jtex.next() == 'l' &&
				jtex.next() == 'l') {

			return new NullLiteral(jtex.getNextPosition() - 4);// explicit null
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading null value.");
		}
	}
}
