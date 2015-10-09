package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NullExpression;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

public class NullExpressionParser implements TokenParser<NullExpression> {

	@Override
	public NullExpression read(JTEX jtex) {
		if (jtex.next() == 'n' &&
				jtex.next() == 'u' &&
				jtex.next() == 'l' &&
				jtex.next() == 'l') {

			return new NullExpression(jtex.getNextPosition() - 4);// explicit null
		} else {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading null value.");
		}
	}
}
