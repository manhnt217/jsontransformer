package wazi.jsontransformer.parser.arithmetic;

import wazi.jsontransformer.expression.arithmetic.ArithmeticExpression;
import wazi.jsontransformer.expression.arithmetic.TermExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.parser.literal.NumberLiteralParser;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class ArithmeticExpressionParser implements TokenParser<ArithmeticExpression> {

	TermExpressionParser termParser;
	FactorExpressionParser factorParser;
	NumberLiteralParser numberLiteralParser;

	public ArithmeticExpressionParser() {
		numberLiteralParser = new NumberLiteralParser();
		factorParser = new FactorExpressionParser(this);
		termParser = new TermExpressionParser(factorParser);
	}

	public ArithmeticExpression read(JTEX jtex) {
		ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
		arithmeticExpression.setStart(jtex.getNextPosition());

		boolean isPositiveTerm = true;

		//read first term
		if (jtex.retrieveNext() == '+') {
			isPositiveTerm = true;
			jtex.next();
			jtex.skipBlank();
		} else if (jtex.retrieveNext() == '-') {
			isPositiveTerm = false;
			jtex.next();
			jtex.skipBlank();
		}

		TermExpression termExpression = termParser.read(jtex);
		termExpression.setIsPositive(isPositiveTerm);
		arithmeticExpression.addTerm(termExpression);
		jtex.skipBlank();


		while (jtex.hasNext() && (jtex.retrieveNext() == '+' || jtex.retrieveNext() == '-')) {
			//read consequence terms
			if (jtex.retrieveNext() == '+') {
				isPositiveTerm = true;
			} else {
				isPositiveTerm = false;
			}
			jtex.next();
			jtex.skipBlank();
			termExpression = termParser.read(jtex);
			termExpression.setIsPositive(isPositiveTerm);
			arithmeticExpression.addTerm(termExpression);
			jtex.skipBlank();
		}
		arithmeticExpression.setEnd(termExpression.getEnd());
		return arithmeticExpression;
	}

}
