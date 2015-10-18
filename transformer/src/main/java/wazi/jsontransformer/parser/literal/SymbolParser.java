package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.SymbolLiteral;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.BaseExpressionParser;

/**
 * Created by wazi on 2015-09-27 027.
 */
public class SymbolParser extends BaseExpressionParser<SymbolLiteral> {

	@Override
	public SymbolLiteral read0(JTEX jtex) {
		if (jtex.next() != '#') throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expect '#'.");
		StringBuilder builder = new StringBuilder("#");
		while (isValidSymbolName(jtex.retrieveNext())) {
				builder.append(jtex.next());
		}
		if (builder.length() < 2) throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expect symbol name");
		return new SymbolLiteral(builder.toString(), jtex.getNextPosition() - builder.length(), jtex.getNextPosition() - 1);
	}

	private boolean isValidSymbolName(char character) {
		return ('0' <= character && character <= '9') ||
				('A' <= character && character <= 'Z') ||
				('a' <= character && character <= 'z') ||
				(character == '_');
	}
}
