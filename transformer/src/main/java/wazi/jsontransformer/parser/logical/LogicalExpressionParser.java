package wazi.jsontransformer.parser.logical;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.logical.LogicalExpression;
import wazi.jsontransformer.expression.operator.Operator;
import wazi.jsontransformer.parser.ComplexExpressionParser;
import wazi.jsontransformer.parser.helper.OperatorParser;

import java.util.Map;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class LogicalExpressionParser extends ComplexExpressionParser<LogicalExpression> {

	@Override
	public LogicalExpression read0(JTEX jtex) {
		jtex.skipBlank();
		LogicalExpression logicalExpression = new LogicalExpression();
		logicalExpression.setStart(jtex.getNextPosition());

		BaseExpression firstExpression = expressionParser.read(jtex);
		logicalExpression.addExpression(new Operator(Operator.Op.OR, -1, -1), firstExpression);
		int jtexEndPosition = jtex.getNextPosition();
		jtex.skipBlank();

		OperatorParser operatorParser = new OperatorParser();
//		boolean hasOperator = false;
		while (true) {
			Operator operator;
			try {
				operator = operatorParser.read(jtex);
			} catch (ParserException e) {
				break;//end of logical expression
			}

			if (!LogicalExpression.OPERATORS.contains(operator.op)) {
				jtex.setNextPosition(operator.getStart()); //rewind if operator is invalid
			}

			jtex.skipBlank();
			boolean isNegative = false;
			if (jtex.retrieveNext() == '!') {//NOT operator
				isNegative = true;
				jtex.next();
			}
			jtex.skipBlank();
			BaseExpression boolExpression = expressionParser.read(jtex);
			if (!isNegative) logicalExpression.addExpression(operator, boolExpression);
			else logicalExpression.addExpression(operator, new LogicalExpression() {
				@Override
				public int getStart() {
					return super.getStart() - 1;//! operator
				}

				@Override
				public Object eval(Map<String, Object> symbolMap) {
					return !(boolean) boolExpression.eval(symbolMap);
				}
			});
//			hasOperator = true;
			jtexEndPosition = jtex.getNextPosition();
			jtex.skipBlank();
		}

//		if (!hasOperator)
//			throw new ParserException(jtexEndPosition, "Logical expression must have at least 1 logical operator (and, or)");

		logicalExpression.setEnd(jtexEndPosition - 1);

		return logicalExpression;
	}

	//
//	public static class NestedLogicalExpressionParser implements TokenParser<LogicalExpression> {
//
//		private final LogicalExpressionParser logicalExpressionParser;
//
//		public NestedLogicalExpressionParser(LogicalExpressionParser logicalExpressionParser) {
//			this.logicalExpressionParser = logicalExpressionParser;
//		}
//
//		@Override
//		public LogicalExpression read(JTEX jtex) {
//			jtex.skipBlank();
//			if (jtex.next() != '(') {
//				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected '(");
//			}
//			jtex.skipBlank();
//			LogicalExpression logicalExpression = logicalExpressionParser.read(jtex);
//			jtex.skipBlank();
//			if (jtex.next() != ')') {
//				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected ')");
//			}
//			logicalExpression.setStart(logicalExpression.getStart() - 1);// for character '('
//			logicalExpression.setEnd(logicalExpression.getEnd() + 1);// for character ')'
//			return logicalExpression;
//		}
//	}
}
