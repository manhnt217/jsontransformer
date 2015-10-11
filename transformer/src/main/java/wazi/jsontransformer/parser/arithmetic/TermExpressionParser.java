package wazi.jsontransformer.parser.arithmetic;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.expression.arithmetic.TermExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.operator.Operator;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.parser.helper.OperatorParser;

/**
 * Created by wazi on 10/9/15.
 */
class TermExpressionParser implements TokenParser<TermExpression> {

	FactorExpressionParser factorParser;
	OperatorParser operatorParser;

	public TermExpressionParser(FactorExpressionParser factorParser) {
		this.factorParser = factorParser;
		this.operatorParser = new OperatorParser();
	}

	@Override
	public TermExpression read(JTEX jtex) {

		TermExpression termExpression = new TermExpression();
		termExpression.setStart(jtex.getNextPosition());

		Operator operator = new Operator(Operator.Op.MULTIPLY, -1, -1);
		termExpression.addFactor(operator, factorParser.read(jtex));
		jtex.skipBlank();

		while (jtex.hasNext() && jtex.retrieveNext() != '+' && jtex.retrieveNext() != '-') {
			try {
				operator = operatorParser.read(jtex);
			} catch (ParserException e) {
				break;
			}
			if (!TermExpression.OPERATORS.contains(operator.op)) {
				jtex.setNextPosition(operator.getStart());//rewind
				break;
			}
			jtex.skipBlank();
			termExpression.addFactor(operator, factorParser.read(jtex));
			jtex.skipBlank();
		}

		termExpression.setEnd(jtex.getNextPosition() - 1);
		return termExpression;
	}

}
