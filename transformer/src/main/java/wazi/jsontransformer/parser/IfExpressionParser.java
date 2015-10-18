package wazi.jsontransformer.parser;

import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.IfExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.logical.LogicalExpressionParser;

/**
 * Created by wazi on 10/17/15.
 */
public class IfExpressionParser extends ComplexExpressionParser<IfExpression> {

	private final LogicalExpressionParser logicalExpressionParser;

	public IfExpressionParser(LogicalExpressionParser logicalExpressionParser) {
		this.logicalExpressionParser = logicalExpressionParser;
	}

	@Override
	public IfExpression read0(JTEX jtex) {
		jtex.skipBlank();
		IfExpression ifExpression = new IfExpression();
		ifExpression.setStart(jtex.getNextPosition());

		if (jtex.next() != 'i' || jtex.next() != 'f') {//if keyword
			throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Unexpected charater while reading 'if' keyword");
		}

		jtex.skipBlank();

		ifExpression.setIfClause(logicalExpressionParser.read(jtex));

		jtex.skipBlank();

		if (jtex.next() != 't' || jtex.next() != 'h' || jtex.next() != 'e' || jtex.next() != 'n') {//then keyword
			throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Unexpected charater while reading 'then' keyword");
		}

		jtex.skipBlank();

		ifExpression.setThenClause(expressionParser.read(jtex));

		jtex.skipBlank();


		if (jtex.next() != 'e' || jtex.next() != 'l' || jtex.next() != 's' || jtex.next() != 'e') {//then keyword
			throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Unexpected charater while reading 'else' keyword");
		}

		jtex.skipBlank();

		BaseExpression elseClause = expressionParser.read(jtex);
		ifExpression.setElseClause(elseClause);

		ifExpression.setEnd(elseClause.getEnd());

		return ifExpression;
	}

//	public static class NestedIfExpressionParser implements TokenParser<IfExpression> {
//
//		private final IfExpressionParser ifExpressionParser;
//
//		public NestedIfExpressionParser(IfExpressionParser ifExpressionParser) {
//			this.ifExpressionParser = ifExpressionParser;
//		}
//
//		@Override
//		public IfExpression read(JTEX jtex) {
//			jtex.skipBlank();
//			if (jtex.next() != '(') {
//				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected '(");
//			}
//			jtex.skipBlank();
//			IfExpression ifExpression = ifExpressionParser.read(jtex);
//			jtex.skipBlank();
//			if (jtex.next() != ')') {
//				throw new UnexpectedCharacterException(jtex.getNextPosition() - 1, jtex.current(), "Expected ')");
//			}
//
//			ifExpression.setStart(ifExpression.getStart() - 1); //for character '('
//			ifExpression.setEnd(ifExpression.getEnd() + 1); // for character ')'
//			return ifExpression;
//		}
//	}
}
