package wazi.jsontransformer.parser.literal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.LiteralExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NumberExpression;
import wazi.jsontransformer.parser.exception.ParserException;

public class NumberParserTest {

	@Test
	public void testReadInteger() {
		NumberParser numberParser = new NumberParser();
		
		JTEX jtex = new JTEX("12384xyf");
		BaseExpression number = numberParser.readExpression(jtex);
		assertEquals(NumberExpression.class, number.getClass());
		assertEquals(12384, number.eval(null));
		assertEquals("12384".length(), jtex.getNextPosition());
		
		assertEquals(23934, numberParser.readExpression(new JTEX("23934df")).eval(null));
	}
	
	@Test
	public void testReadDouble() {
		String input = "23934e11 sad";
		
		NumberParser numberParser = new NumberParser();
		
		JTEX jtex = new JTEX(input);
        BaseExpression number = numberParser.readExpression(jtex);
		
		assertEquals(NumberExpression.class, number.getClass());
		assertEquals(23934e11, number.eval(null));
		assertEquals("23934e11".length(), jtex.getNextPosition());
		
		assertEquals(0.5, numberParser.readExpression(new JTEX("+0.5")).eval(null));
		assertEquals(0.5e12, numberParser.readExpression(new JTEX("+0.5e12")).eval(null));
		assertEquals(12e-12, numberParser.readExpression(new JTEX("12E-12")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException1() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readExpression(new JTEX("-a")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException2() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readExpression(new JTEX("-12.ee2")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException3() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readExpression(new JTEX("-.e10")).eval(null));
	}
}
