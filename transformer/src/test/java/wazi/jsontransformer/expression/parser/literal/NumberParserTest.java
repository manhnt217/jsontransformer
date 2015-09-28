package wazi.jsontransformer.expression.parser.literal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.ParserException;

public class NumberParserTest {

	@Test
	public void testReadInteger() {
		NumberParser numberParser = new NumberParser();
		
		JTEX jtex = new JTEX("12384xyf");
		Object number = numberParser.readExpression(jtex);
		assertEquals(Integer.class, number.getClass());
		assertEquals(12384, number);
		assertEquals("12384".length(), jtex.getNextPosition());
		
		assertEquals(23934, numberParser.readExpression(new JTEX("23934df")));
	}
	
	@Test
	public void testReadDouble() {
		String input = "23934e11 sad";
		
		NumberParser numberParser = new NumberParser();
		
		JTEX jtex = new JTEX(input);
		Object number = numberParser.readExpression(jtex);
		
		assertEquals(Double.class, number.getClass());
		assertEquals(23934e11, number);
		assertEquals("23934e11".length(), jtex.getNextPosition());
		
		assertEquals(0.5, numberParser.readExpression(new JTEX("+0.5")));
		assertEquals(0.5e12, numberParser.readExpression(new JTEX("+0.5e12")));
		assertEquals(12e-12, numberParser.readExpression(new JTEX("12E-12")));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException1() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readExpression(new JTEX("-a")));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException2() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readExpression(new JTEX("-12.ee2")));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException3() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readExpression(new JTEX("-.e10")));
	}
}
