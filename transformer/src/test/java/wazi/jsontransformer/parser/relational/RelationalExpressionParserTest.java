package wazi.jsontransformer.parser.relational;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.relational.RelationalExpression;
import wazi.jsontransformer.parser.ExpressionParser;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by wazi on 2015-10-11 011.
 */
public class RelationalExpressionParserTest {

	@Test
	public void testReadExpression() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("- 1 > 2 * 3"));
		assertEquals(0, relationalExpression.getStart());
		assertEquals(10, relationalExpression.getEnd());
	}

	@Test
	public void testEvaluateExpression() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("- 64 / (10 - 2) >= -(2 * 3 / #a + 6)"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});
		assertTrue(val instanceof Boolean);
		assertEquals(true, (Boolean) val);
	}

	@Test
	public void testEvaluateExpression2() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("64 / (10 - 2) != 3 * 3 / #a + 6"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});

		assertEquals(0, relationalExpression.getStart());
		assertEquals(30, relationalExpression.getEnd());
		assertTrue(val instanceof Boolean);
		assertEquals(true, (Boolean) val);
	}

	@Test
	public void testEvaluateExpression3() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		RelationalExpressionParser parser = expressionParser.relationalExpressionParser;
		RelationalExpression relationalExpression = parser.read(new JTEX("(64 / (10 - 2) = 3 * 3 / #a + 6) = false"));
		Object val = relationalExpression.eval(new HashMap<String, Object>() {{
			put("#a", 3);
		}});

		assertEquals(0, relationalExpression.getStart());
		assertEquals(39, relationalExpression.getEnd());
		assertTrue(val instanceof Boolean);
		assertEquals(true, (Boolean) val);
	}
}