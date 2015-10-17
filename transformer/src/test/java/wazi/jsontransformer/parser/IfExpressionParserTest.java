package wazi.jsontransformer.parser;

import org.junit.Test;
import wazi.jsontransformer.expression.IfExpression;
import wazi.jsontransformer.expression.jtex.JTEX;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wazi on 10/17/15.
 */
public class IfExpressionParserTest {

	@Test
	public void testReadExpression() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		IfExpressionParser ifExpressionParser = expressionParser.ifExpressionParser;
		IfExpression ifExpression = ifExpressionParser.read(new JTEX(" if 3 < 2 then 2 else (5 + 11)  "));
		assertEquals(1, ifExpression.getStart());
		assertEquals(29, ifExpression.getEnd());
	}

	@Test
	public void testReadExpression2() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		IfExpressionParser ifExpressionParser = expressionParser.ifExpressionParser;
		IfExpression ifExpression = ifExpressionParser.read(new JTEX(" if(3 < 2)then 2 else 5 "));
		assertEquals(1, ifExpression.getStart());
		assertEquals(22, ifExpression.getEnd());
	}

	@Test
	public void testEvaluateExpression() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		IfExpressionParser ifExpressionParser = expressionParser.ifExpressionParser;
		IfExpression ifExpression = ifExpressionParser.read(new JTEX(" if(3 != 2)then if false = if true then false else true then 11 else 99 else 5 "));

		assertEquals(11, ifExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression2() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		IfExpressionParser ifExpressionParser = expressionParser.ifExpressionParser;
		IfExpression ifExpression = ifExpressionParser.read(new JTEX("if ( 2 + #a > 8) then ((if null then 4 else 'abc')) else if false then 45.5 else 45"));

		assertEquals(11, ifExpression.eval(new HashMap<String, Object>() {
			{
				put("#a", 11);
			}
		}));
	}
}