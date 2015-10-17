package wazi.jsontransformer.parser.logical.relational;

import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.logical.relational.RelationalExpression;
import wazi.jsontransformer.expression.operator.Operator;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.helper.OperatorParser;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class RelationalExpressionParser implements TokenParser<RelationalExpression> {

	private final MultiChoiceParser<BaseExpression> expressionParser = new MultiChoiceParser<>(false);

	public void addSubParsers(TokenParser<? extends BaseExpression>... parsers) {
		expressionParser.addAllParsers(parsers);
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
		boolean hasOperator = false;
		while (true) {
			Operator operator;
			try {
				operator = operatorParser.read(jtex);
			} catch (ParserException e) {
				break;
			}
			if (!RelationalExpression.OPERATORS.contains(operator.op)) {
				jtex.setNextPosition(operator.getStart());//rewind if operator is invalid
				break;
			}
			jtex.skipBlank();
			relationalExpression.addExpression(operator, expressionParser.read(jtex));
			hasOperator = true;
			jtex.skipBlank();
		}

		if (!hasOperator)
			throw new ParserException(jtex.getNextPosition(), "Relational exp must have at least 1 relational operator (>, <, =, <=, =, !=)");

		relationalExpression.setEnd(jtex.getNextPosition() - 1);
		return relationalExpression;
	}

	public static class NestedRelationalExpressionParser implements TokenParser<RelationalExpression> {

		private final RelationalExpressionParser relationalExpressionParser;

		public NestedRelationalExpressionParser(RelationalExpressionParser relationalExpressionParser) {
			this.relationalExpressionParser = relationalExpressionParser;
		}

		@Override
		public RelationalExpression read(JTEX jtex) {
			jtex.skipBlank();
			if (jtex.next() != '(') {
				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected '(");
			}
			jtex.skipBlank();
			RelationalExpression relationalExpression = relationalExpressionParser.read(jtex);
			jtex.skipBlank();
			if (jtex.next() != ')') {
				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected ')");
			}

			relationalExpression.setStart(relationalExpression.getStart() - 1); //for character '('
			relationalExpression.setEnd(relationalExpression.getEnd() + 1); // for character ')'
			return relationalExpression;
		}
	}
}
