package wazi.jsontransformer.parser.helper;

import wazi.jsontransformer.expression.Token;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.parser.TokenParser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wazi on 2015-09-24 024.
 */
public class MultiChoiceParser<T extends Token> implements TokenParser<T> {

	List<TokenParser<? extends T>> parsers;

	public MultiChoiceParser() {
		this((TokenParser[])null);
	}

	public MultiChoiceParser(TokenParser<? extends T>... parsers) {
		this.parsers = new LinkedList<>();
		if (parsers != null) {
			for (TokenParser<? extends T> parser : parsers) {
				this.parsers.add(parser);
			}
		}
	}

	public void addParser(TokenParser<? extends T> parser) {
		this.parsers.add(parser);
	}

	@Override
	public T read(JTEX jtex) {

		ParserException ex = new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "");

		for (TokenParser<? extends T> parser : parsers) {
			try {
				JTEX jtexCopy = new JTEX(jtex.getString(), jtex.getNextPosition());
				T expression = parser.read(jtexCopy);
				jtex.setNextPosition(jtexCopy.getNextPosition());
				return expression;
			} catch (ParserException e) {
				if (e.getPosition() > ex.getPosition()) {
					ex = e;
				}
			}
		}

		throw ex;
	}
}
