package wazi.jsontransformer.parser.arithmetic;

import wazi.jsontransformer.expression.arithmetic.ArithmeticExpression;
import wazi.jsontransformer.expression.arithmetic.TermExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.BaseExpressionParser;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class ArithmeticExpressionParser extends BaseExpressionParser<ArithmeticExpression> {

	TermExpressionParser termParser;
	FactorExpressionParser factorParser;

	public ArithmeticExpressionParser() {
		factorParser = new FactorExpressionParser(this);
		termParser = new TermExpressionParser(factorParser);
	}

	public ArithmeticExpression read0(JTEX jtex) {
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
			isPositiveTerm = jtex.retrieveNext() == '+';
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
