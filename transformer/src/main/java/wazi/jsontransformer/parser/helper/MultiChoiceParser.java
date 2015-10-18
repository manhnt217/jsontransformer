package wazi.jsontransformer.parser.helper;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.Token;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.TokenParser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wazi on 2015-09-24 024.
 */
public class MultiChoiceParser<T extends Token> implements TokenParser<T> {

	List<TokenParser<? extends T>> parsers;
	private final boolean longestMatch;//return longest matched token, default = false

	public MultiChoiceParser(boolean longestMatch, TokenParser<? extends T>... parsers) {
		this.longestMatch = longestMatch;
		this.parsers = new LinkedList<>();
		if (parsers != null) {
			for (TokenParser<? extends T> parser : parsers) {
				this.parsers.add(parser);
			}
		}
	}

	public MultiChoiceParser() {
		this(true, (TokenParser[]) null);
	}

	public MultiChoiceParser(boolean longestMatch) {
		this(longestMatch, (TokenParser[]) null);
	}

	public MultiChoiceParser(TokenParser<? extends T>... parsers) {
		this(true, parsers);
	}

	public void addParser(TokenParser<? extends T> parser) {
		this.parsers.add(parser);
	}

	public void addParsers(TokenParser<? extends T>... parsers) {
		this.addParsers(Arrays.asList(parsers));
	}
	public void addParsers(List<TokenParser<? extends T>> parsers) {
		this.parsers.addAll(parsers);
	}

	public List<TokenParser<? extends T>> getParsers() {
		return parsers;
	}

	@Override
	public T read(JTEX jtex) {

		final int jtexStartPosition = jtex.getNextPosition();
		ParserException ex = new UnexpectedCharacterException(jtexStartPosition, jtex.retrieveNext(), "");
		T exp = null;
		int furthestPosition = 0;

		for (TokenParser<? extends T> parser : parsers) {
			try {
				JTEX jtexCopy = new JTEX(jtex.getString(), jtex.getNextPosition());
				T expression = parser.read(jtexCopy);
				if (!longestMatch){
					jtex.setNextPosition(jtexCopy.getNextPosition());
					return expression;
				} else if (jtexCopy.getNextPosition() > furthestPosition) {
					furthestPosition = jtexCopy.getNextPosition();
					exp = expression;
				}
			} catch (ParserException e) {
				if (e.getPosition() > ex.getPosition()) {
					ex = e;
				}
			}
		}

		if (exp != null) {
			jtex.setNextPosition(furthestPosition);
			return exp;
		}
		throw ex;
	}
}
