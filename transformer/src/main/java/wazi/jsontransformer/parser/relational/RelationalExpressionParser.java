package wazi.jsontransformer.parser.relational;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.operator.Operator;
import wazi.jsontransformer.expression.relational.RelationalExpression;
import wazi.jsontransformer.parser.ExpressionParser;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.helper.OperatorParser;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class RelationalExpressionParser implements TokenParser<RelationalExpression> {

	private MultiChoiceParser<BaseExpression> expressionParser;

	public RelationalExpressionParser(ExpressionParser expressionParser) {
		this.expressionParser = new MultiChoiceParser<BaseExpression>() {
			{
				addParser(expressionParser.nullExpressionParser);
				addParser(expressionParser.booleanExpressionParser);
				addParser(expressionParser.stringExpressionParser);
				addParser(expressionParser.symbolParser);
				addParser(expressionParser.arithmeticExpressionParser);
				addParser(expressionParser.functionParser);
				addParser(new NestedRelationalExpressionParser(RelationalExpressionParser.this));
			}
		};
	}

	@Override
	public RelationalExpression read(JTEX jtex) {
		jtex.skipBlank();
		RelationalExpression relationalExpression = new RelationalExpression();
		relationalExpression.setStart(jtex.getNextPosition());
		//read first expression
		relationalExpression.addFirstExpression(expressionParser.read(jtex));
		jtex.skipBlank();
		OperatorParser operatorParser = new OperatorParser();
		while (true) {
			Operator operator;
			try {
				operator = operatorParser.read(jtex);
			} catch (ParserException e) {
				break;
			}
			if (!RelationalExpression.OPERATORS.contains(operator.op)) {
				jtex.setNextPosition(operator.getStart());//rewind
				break;
			}
			jtex.skipBlank();
			relationalExpression.addExpression(operator, expressionParser.read(jtex));
			jtex.skipBlank();
		}

		relationalExpression.setEnd(jtex.getNextPosition() - 1);
		return relationalExpression;
	}

	static class NestedRelationalExpressionParser implements TokenParser<RelationalExpression> {

		private final RelationalExpressionParser relationalExpressionParser;

		public NestedRelationalExpressionParser(RelationalExpressionParser relationalExpressionParser) {
			this.relationalExpressionParser = relationalExpressionParser;
		}

		@Override
		public RelationalExpression read(JTEX jtex) {
			jtex.skipBlank();
			if (jtex.next() != '(') {
				throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Expected '(");
			}
			jtex.skipBlank();
			RelationalExpression relationalExpression = relationalExpressionParser.read(jtex);
			jtex.skipBlank();
			if (jtex.next() != ')') {
				throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Expected ')");
			}
			return relationalExpression;
		}
	}
}
