package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.Token;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

/**
 * Created by wazi on 10/8/15.
 */
public class FixTokenParser implements TokenParser {

	String token;

	public FixTokenParser(String token) {
		this.token = token;
	}

	@Override
	public Token read(JTEX jtex) {
		for (int i = 0; i < token.length(); i++) {
			if (jtex.next() != token.charAt(i)) {
				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected character '" + token
						.charAt(i) + "'");
			}
		}
		return new Token(jtex.getNextPosition() - this.token.length(), jtex.getNextPosition() - 1);
	}
}
