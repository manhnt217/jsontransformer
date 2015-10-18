package wazi.jsontransformer.parser;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;

import java.util.List;

/**
 * Created by wazi on 2015-10-17 017.
 */
public abstract class ComplexExpressionParser<T extends BaseExpression> implements TokenParser<T> {

	protected MultiChoiceParser<BaseExpression> expressionParser = new MultiChoiceParser<>();
	int nestedLevel = 0;

	@Override
	public final T read(JTEX jtex) {
		jtex.skipBlank();
		int jtexNextPosition = jtex.getNextPosition();
		try {
			for (int i = 0; i < nestedLevel; i++) {
				readOpenParenthese(jtex);
			}

			T expression = read0(jtex);

			for (int i = 0; i < nestedLevel; i++) {
				readCloseParenthese(jtex);
			}

			expression.setStart(expression.getStart() - nestedLevel);
			expression.setEnd(expression.getEnd() + nestedLevel);
			return expression;
		} catch (ParserException e) {
			//rewind jtex
			jtex.setNextPosition(jtexNextPosition);

			if (jtex.retrieveNext() == '(') {
				//read open parenthese
				readOpenParenthese(jtex);
				jtex.skipBlank();
				//read expression
				T expression = read(jtex);
				//read close parenthese
				readCloseParenthese(jtex);
				//return value
				expression.setStart(expression.getStart() - 1);// for character '('
				expression.setEnd(expression.getEnd() + 1);// for character ')'
				return expression;
			} else {
				throw e;
			}
		}
	}

	public void addSubParsers(TokenParser<? extends BaseExpression>... parsers) {
		expressionParser.addParsers(parsers);
	}

	public void addSubParsers(List<TokenParser<? extends BaseExpression>> parsers) {
		expressionParser.addParsers(parsers);
	}

	/**
	 * Real method for reading expression
	 *
	 * @param jtex
	 * @return Expresion
	 */
	protected abstract T read0(JTEX jtex);

	private void readOpenParenthese(JTEX jtex) {
		jtex.skipBlank();
		if (jtex.next() != '(') {
			throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected '(");
		}
	}

	private void readCloseParenthese(JTEX jtex) {
		jtex.skipBlank();
		if (jtex.next() != ')') {
			throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected ')");
		}
	}

	public int getNestedLevel() {
		return nestedLevel;
	}

	public void setNestedLevel(int nestedLevel) {
		this.nestedLevel = nestedLevel;
	}

	public List<TokenParser<? extends BaseExpression>> getSubParsers() {
		return expressionParser.getParsers();
	}
}
