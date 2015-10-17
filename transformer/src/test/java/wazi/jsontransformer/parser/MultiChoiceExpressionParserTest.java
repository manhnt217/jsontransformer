package wazi.jsontransformer.parser;

import org.junit.Test;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.literal.LiteralExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.BooleanLiteralParser;
import wazi.jsontransformer.parser.literal.NumberLiteralParser;
import wazi.jsontransformer.parser.literal.StringLiteralParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by wazi on 9/25/15.
 */
public class MultiChoiceExpressionParserTest {

	@Test
	public void testReadExpression() throws Exception {
		MultiChoiceParser<BaseExpression> parser = new MultiChoiceParser(new NumberLiteralParser(), new StringLiteralParser(), new BooleanLiteralParser());
		JTEX jtex = new JTEX("1234e13adf");
		BaseExpression ex1 = parser.read(jtex);
		assertEquals(0, ex1.getStart());
		assertEquals(6, ex1.getEnd());
		assertEquals(1234e13, ex1.eval(null));
		assertEquals(7, jtex.getNextPosition());

		BaseExpression ex2 = parser.read(new JTEX("'Hello \\'World\\'㊇'"));
		assertEquals(0, ex2.getStart());
		assertEquals(17, ex2.getEnd());
		assertEquals("Hello 'World'\u3287", ex2.eval(null));


		try {
			parser.read(new JTEX("h 234a"));
			fail("Should throw exception");
		} catch (UnexpectedCharacterException e) {
			assertEquals(1, e.getPosition());
		}

		try {
			parser.read(new JTEX("'- 234a"));
		} catch (EndOfJtexException e) {
			assertEquals(7, e.getPosition());
		}

		BaseExpression ex5 = parser.read(new JTEX("true"));
		assertEquals(0, ex5.getStart());
		assertEquals(3, ex5.getEnd());
		assertEquals(true, ex5.eval(null));
	}

	@Test
	public void test2() {
		JTEX jtex = new JTEX("ửaf-0234.3e11'Test'", 3);
		NumberThenStringParser parser = new NumberThenStringParser();
		BaseExpression expression = parser.read(jtex);
		assertEquals(3, expression.getStart());
		assertEquals(18, expression.getEnd());
		assertEquals("-2.343E13Test", expression.eval(null));
	}

	private static class NumberThenStringParser implements TokenParser<LiteralExpression> {

		@Override
		public LiteralExpression read(JTEX jtex) {
			int start = jtex.getNextPosition();
			NumberLiteralParser numberLiteralParser = new NumberLiteralParser();
			StringLiteralParser stringLiteralParser = new StringLiteralParser();
			return new LiteralExpression(numberLiteralParser.read(jtex).eval(null).toString() + stringLiteralParser.read(jtex).eval(null), start, jtex.getNextPosition() - 1);
		}
	}
}