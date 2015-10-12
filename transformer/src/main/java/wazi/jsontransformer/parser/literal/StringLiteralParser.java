package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.StringLiteral;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

public class StringLiteralParser implements TokenParser<StringLiteral> {

	StringBuilder builder;

	@Override
	public StringLiteral read(JTEX jtex) {
		int start = jtex.getNextPosition();
		if (jtex.next() != '"')
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading string. Expected \".");
		builder = new StringBuilder();
		Character nextChar;
		while ((nextChar = readChar(jtex)) != null) {
			builder.append(nextChar);
		}
		jtex.next();//character quote (")

		return new StringLiteral(builder.toString(), start, jtex.getNextPosition() - 1);// 2 is for open and close quote character
	}

	public Character readChar(JTEX jtex) {
		if (jtex.retrieveNext() == '"') {
			return null;// end of string
		}
		if (jtex.retrieveNext() != '\\')
			return jtex.next();

		jtex.next(); // character '\'
		//@formatter:off
		switch (jtex.next()) {
			case 't':
				return '\t';
			case 'b':
				return '\b';
			case 'n':
				return '\n';
			case 'r':
				return '\r';
			case 'f':
				return '\f';
			case '\'':
				return '\'';
			case '"':
				return '"';
			case '\\':
				return '\\';
			case 'u':
				return readUnicodeCharacter(jtex);
			default:
				throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading escaped character.");
		}
		//@formatter:on
	}

	public Character readUnicodeCharacter(JTEX jtex) {
		int charCode = 0;
		int hexChar = 0;
		int degree = 12;
		while (degree >= 0 && (hexChar = charToHex(jtex.next())) != -1) {
			charCode += hexChar << degree;
			degree -= 4;
		}
		if (hexChar == -1) {
			throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.current(), "Exception while reading unicode character.");
		}
		return (char) charCode;
	}

	public int charToHex(char c) {
		if ('0' <= c && c <= '9')
			return c - '0';
		else if (('a' <= c && c <= 'f') || ('A' <= c && c <= 'F'))
			return Character.toUpperCase(c) - 'A' + 10;
		else
			return -1;
	}
}
