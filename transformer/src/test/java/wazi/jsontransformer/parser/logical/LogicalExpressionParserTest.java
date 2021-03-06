package wazi.jsontransformer.parser.logical;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.logical.LogicalExpression;
import wazi.jsontransformer.parser.ExpressionParser;

import static org.junit.Assert.assertEquals;

/**
 * Created by wazi on 2015-10-15 015.
 */
public class LogicalExpressionParserTest {

	@Test
	public void testReadExpression() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX(" 1 > 3 and 2 - 5 < 6"));
		assertEquals(1, logicalExpression.getStart());
		assertEquals(19, logicalExpression.getEnd());
	}

	@Test
	public void testReadExpression2() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("3 > 5 or 6 < 7"));
		assertEquals(0, logicalExpression.getStart());
		assertEquals(13, logicalExpression.getEnd());
	}

	@Test
	public void testEvaluateExpression() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX(" 1 < 3 and (((2 - 5 < 6) != true) = false) != 'true'"));
		assertEquals(false, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression2() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("(((((2 - 5 < 6) != true)))) and 1"));
		assertEquals(0, logicalExpression.getStart());
		assertEquals(32, logicalExpression.getEnd());
		assertEquals(false, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression3() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("18 and 0"));
		assertEquals(false, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression4() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("0.0 or 1"));
		assertEquals(true, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression5() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("0 and 0"));
		assertEquals(false, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression6() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("11.5 and false"));
		assertEquals(false, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression7() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("'false' or true"));
		assertEquals(true, logicalExpression.eval(null));
	}

	@Test
	public void testEvaluateExpression8() throws Exception {
		ExpressionParser expressionParser = new ExpressionParser();
		LogicalExpressionParser logicalExpressionParser = expressionParser.logicalExpressionParser;
		LogicalExpression logicalExpression = logicalExpressionParser.read(new JTEX("null or null"));
		assertEquals(false, logicalExpression.eval(null));
	}
}