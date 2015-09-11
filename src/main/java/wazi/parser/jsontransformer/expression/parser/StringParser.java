package wazi.parser.jsontransformer.expression.parser;

import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

public class StringParser {

	StringBuilder builder;

	public String readString(JTEX jtex) {
		if (jtex.next() != '"')
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Exception while reading string");
		builder = new StringBuilder();
		Character nextChar = null;
		while ((nextChar = readChar(jtex)) != null) {
			builder.append(nextChar);
		}

		return builder.toString();
	}

	Character readChar(JTEX jtex) {
		if (jtex.retrieveNext() == '"') {
			jtex.next();
			return null;// end of string
		}
		if (jtex.retrieveNext() != '\\')
			return jtex.next();
		
		jtex.next(); // character '\'
		//@formatter:off
		switch (jtex.next()) {
			case 't':	return '\t';
			case 'b':	return '\b';
			case 'n':	return '\n';
			case 'r':	return '\r';
			case 'f':	return '\f';
			case '\'':	return '\'';
			case '"':	return '"';
			case '\\':	return '\\';
			case 'u':	return readUnicodeCharacter(jtex);
			default:
				throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Exception while reading escaped character.");
		}
		//@formatter:on
	}

	Character readUnicodeCharacter(JTEX jtex) {
		int charCode = 0;
		int hexChar = 0;
		int degree = 12;
		while (degree >= 0 && (hexChar = charToHex(jtex.next())) != -1) {
			charCode += hexChar << degree;
			degree -= 4;
		}
		if (hexChar == -1) {
			throw new UnexpectedCharacterException(jtex.getPosition(), jtex.current(), "Exception while reading unicode character.");
		}
		return (char) charCode;
	}

	int charToHex(char c) {
		if ('0' <= c && c <= '9')
			return c - '0';
		else if (('a' <= c && c <= 'f') || ('A' <= c && c <= 'F'))
			return Character.toUpperCase(c) - 'A' + 10;
		else
			return -1;
	}
}
