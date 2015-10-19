package wazi.jsontransformer.parser.logical.relational;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.logical.relational.RelationalExpression;
import wazi.jsontransformer.parser.ExpressionParser;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by wazi on 2015-10-11 011.
 */
public class RelationalExpressionParserTest {

	@Test
	public void testReadExpression() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("- 1 > 2 * 3"));
		assertEquals(0, relationalExpression.getStart());
		assertEquals(10, relationalExpression.getEnd());
	}

	@Test
	public void testEvaluateExpression() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("- 64 / (10 - 2) >= -(2 * 3 / #a + 6)"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});
		assertTrue(val instanceof Boolean);
		assertEquals(true, val);
	}

	@Test
	public void testEvaluateExpression2() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("64 / (10 - 2) != 3 * 3 / #a + 6"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});

		assertEquals(0, relationalExpression.getStart());
		assertEquals(30, relationalExpression.getEnd());
		assertTrue(val instanceof Boolean);
		assertEquals(true, val);
	}

	@Test
	public void testEvaluateExpression3() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("((((64 / (10 - 2) = 3 * 3 / #a + 6) = false)))"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});

		assertEquals(0, relationalExpression.getStart());
		assertEquals(45, relationalExpression.getEnd());
		assertTrue(val instanceof Boolean);
		assertEquals(true, val);
	}

	@Test
	public void testEvaluateExpression4() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("false = ((64 / (10 - 2) = 3 * 3 / #a + 6) = false)"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});

		assertEquals(0, relationalExpression.getStart());
		assertEquals(49, relationalExpression.getEnd());
		assertTrue(val instanceof Boolean);
		assertEquals(false, val);
	}

	@Test
	public void testEvaluateExpression5() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("(7+8) < 15"));
		assertEquals(false, relationalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression6() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("'12.1' = 12.1"));
		assertEquals(true, relationalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression7() {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("false = if true then false else true"));
		assertEquals(true, relationalExpression.eval(null));
	}
}