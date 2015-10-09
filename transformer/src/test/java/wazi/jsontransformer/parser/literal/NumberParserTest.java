package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NumberExpression;
import wazi.jsontransformer.exception.parser.ParserException;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {

	@Test
	public void testReadInteger() {
		NumberExpressionParser numberExpressionParser = new NumberExpressionParser();
		
		JTEX jtex = new JTEX("12384xyf");
		BaseExpression number = numberExpressionParser.read(jtex);
		assertEquals(NumberExpression.class, number.getClass());
		assertEquals(12384, number.eval(null));
		assertEquals("12384".length(), jtex.getNextPosition());
		
		assertEquals(23934, numberExpressionParser.read(new JTEX("23934df")).eval(null));
	}
	
	@Test
	public void testReadDouble() {
		String input = "23934e11 sad";
		
		NumberExpressionParser numberExpressionParser = new NumberExpressionParser();
		
		JTEX jtex = new JTEX(input);
        BaseExpression number = numberExpressionParser.read(jtex);
		
		assertEquals(NumberExpression.class, number.getClass());
		assertEquals(23934e11, number.eval(null));
		assertEquals("23934e11".length(), jtex.getNextPosition());
		
		assertEquals(0.5, numberExpressionParser.read(new JTEX("+0.5")).eval(null));
		assertEquals(0.5e12, numberExpressionParser.read(new JTEX("+0.5e12")).eval(null));
		assertEquals(12e-12, numberExpressionParser.read(new JTEX("12E-12")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException1() {
		NumberExpressionParser numberExpressionParser = new NumberExpressionParser();
		assertEquals(-21, numberExpressionParser.read(new JTEX("-a")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException2() {
		NumberExpressionParser numberExpressionParser = new NumberExpressionParser();
		assertEquals(-21, numberExpressionParser.read(new JTEX("-12.ee2")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException3() {
		NumberExpressionParser numberExpressionParser = new NumberExpressionParser();
		assertEquals(-21, numberExpressionParser.read(new JTEX("-.e10")).eval(null));
	}
}
