package wazi.jsontransformer.parser.forexp;

import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.forexp.Yield;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.BaseExpressionParser;

/**
 * Created by wazi on 10/19/15.
 */
public class YieldExpressionParser extends BaseExpressionParser<Yield> {
	@Override
	protected Yield read0(JTEX jtex) {
		jtex.skipBlank();
		Yield yield = new Yield();
		yield.setStart(jtex.getNextPosition());

		if (jtex.next() != 'y' || jtex.next() != 'i' || jtex.next() != 'e' || jtex.next() != 'l' || jtex.next() != 'l' || jtex.next() != 'd') {
			throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Unexpected charater while reading 'yield' keyword");
		}

		BaseExpression baseExpression = expressionParser.read(jtex);

		yield.setEnd(baseExpression.getEnd());

		return yield;
	}
}
