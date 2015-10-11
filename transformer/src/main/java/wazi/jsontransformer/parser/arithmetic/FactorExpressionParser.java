package wazi.jsontransformer.parser.arithmetic;

import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.arithmetic.ArithmeticExpression;
import wazi.jsontransformer.expression.arithmetic.FactorExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.SymbolParser;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.parser.literal.NumberLiteralParser;

/**
 * Created by wazi on 10/9/15.
 */
class FactorExpressionParser implements TokenParser<FactorExpression> {

	private final NumberLiteralParser numberLiteralParser;
	private final SymbolParser symbolExpressionParser;
	private final ArithmeticExpressionParser arithmeticExpressionParser;

	public FactorExpressionParser(ArithmeticExpressionParser arithmeticExpressionParser) {
		this.numberLiteralParser = new NumberLiteralParser();
		symbolExpressionParser = new SymbolParser();
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
				numberLiteralParser,
				symbolExpressionParser,
				//custom arithmetic expression parser
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
