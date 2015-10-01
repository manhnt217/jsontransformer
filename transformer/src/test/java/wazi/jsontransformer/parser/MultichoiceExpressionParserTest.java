package wazi.jsontransformer.parser;

import org.junit.Test;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.LiteralExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.exception.EndOfJtexException;
import wazi.jsontransformer.parser.exception.UnexpectedCharacterException;
import wazi.jsontransformer.parser.literal.BooleanParser;
import wazi.jsontransformer.parser.literal.NumberParser;
import wazi.jsontransformer.parser.literal.StringParser;

import static org.junit.Assert.*;

/**
 * Created by wazi on 9/25/15.
 */
public class MultichoiceExpressionParserTest {

	@Test
	public void testReadExpression() throws Exception {
		MultichoiceExpressionParser parser = new MultichoiceExpressionParser(new NumberParser(), new StringParser(), new BooleanParser());
		BaseExpression ex1 = parser.readExpression(new JTEX("1234e13adf"));
		assertEquals(0, ex1.getStart());
		assertEquals(6, ex1.getEnd());
		assertEquals(1234e13, ex1.eval(null));

		BaseExpression ex2 = parser.readExpression(new JTEX("\"Hello \\\"World\\\"\\u3287\""));
		assertEquals(0, ex2.getStart());
		assertEquals(22, ex2.getEnd());
		assertEquals("Hello \"World\"\u3287", ex2.eval(null));


		try {
			parser.readExpression(new JTEX("h 234a"));
			fail("Should throw exception");
		} catch (UnexpectedCharacterException e) {
			assertEquals(1, e.getPosition());
		}

		try {
			parser.readExpression(new JTEX("\"- 234a"));
		} catch (EndOfJtexException e) {
			assertEquals(7, e.getPosition());
		}

		BaseExpression ex5 = parser.readExpression(new JTEX("true"));
		assertEquals(0, ex5.getStart());
		assertEquals(3, ex5.getEnd());
		assertEquals(true, ex5.eval(null));
	}

	@Test
	public void test2() {
		JTEX jtex = new JTEX("ửaf-0234.3e11\"Test\"", 3);
		NumberThenStringParser parser = new NumberThenStringParser();
		BaseExpression expression = parser.readExpression(jtex);
		assertEquals(3, expression.getStart());
		assertEquals(18, expression.getEnd());
		assertEquals("-2.343E13Test", expression.eval(null));
	}

	private static class NumberThenStringParser implements ExpressionParser {

		@Override
		public BaseExpression readExpression(JTEX jtex) {
			int start = jtex.getNextPosition();
			NumberParser numberParser = new NumberParser();
			StringParser stringParser = new StringParser();
			return new LiteralExpression(numberParser.readExpression(jtex).eval(null).toString() + stringParser.readExpression(jtex).eval(null), start, jtex.getNextPosition() - 1);
		}
	}
}