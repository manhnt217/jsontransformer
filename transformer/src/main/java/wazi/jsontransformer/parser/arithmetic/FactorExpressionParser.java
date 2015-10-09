package wazi.jsontransformer.parser.arithmetic;

import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.arithmetic.ArithmeticExpression;
import wazi.jsontransformer.expression.arithmetic.FactorExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.MultiChoiceParser;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.parser.literal.NumberExpressionParser;

/**
 * Created by wazi on 10/9/15.
 */
public class FactorExpressionParser implements TokenParser<FactorExpression> {

	private final NumberExpressionParser numberExpressionParser;
	private final ArithmeticExpressionParser arithmeticExpressionParser;

	public FactorExpressionParser(NumberExpressionParser numberExpressionParser, ArithmeticExpressionParser arithmeticExpressionParser) {
		this.numberExpressionParser = numberExpressionParser;
		this.arithmeticExpressionParser = arithmeticExpressionParser;
	}

	@Override
	public FactorExpression read(JTEX jtex) {

		FactorExpression factorExpression = new FactorExpression();
		factorExpression.setStart(jtex.getNextPosition());

		boolean isPositiveFactor = true;
		if (jtex.retrieveNext() == '+') {
			isPositiveFactor = true;
			jtex.next();
		} else if (jtex.retrieveNext() == '-') {
			isPositiveFactor = false;
			jtex.next();
		}
		factorExpression.setIsPositive(isPositiveFactor);

		jtex.skipBlank();
		MultiChoiceParser<BaseExpression> multiChoiceParser = new MultiChoiceParser<BaseExpression>(
				numberExpressionParser,
				jtExp -> {
					//check parenthesis
					if (jtExp.retrieveNext() != '(') {
						throw new UnexpectedCharacterException(jtExp.getNextPosition(), jtExp.retrieveNext(), "Expected open parenthese '('.");
					}
					jtExp.next();//parenthese '('

					jtExp.skipBlank();
					ArithmeticExpression arithmeticExpression = arithmeticExpressionParser.read(jtExp);
					jtExp.skipBlank();

					if (jtExp.retrieveNext() != ')') {
						throw new UnexpectedCharacterException(jtExp.getNextPosition(), jtExp.retrieveNext(), "Expected close parenthese ')'.");
					}
					jtExp.next();//parenthese ')'

					return arithmeticExpression;
				}
		);

		factorExpression.setBaseExpression(multiChoiceParser.read(jtex));
		factorExpression.setEnd(jtex.getNextPosition() - 1);
		return factorExpression;
	}
}