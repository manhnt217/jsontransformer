package wazi.parser.jsontransformer.expression.parser.literal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.jtex.JTEX;
import wazi.parser.jsontransformer.expression.parser.exception.ParserException;
import wazi.parser.jsontransformer.expression.parser.literal.NumberParser;

public class NumberParserTest {

	@Test
	public void testReadInteger() {
		NumberParser numberParser = new NumberParser();
		
		JTEX jtex = new JTEX("12384xyf");
		Object number = numberParser.readNumber(jtex);
		assertEquals(Integer.class, number.getClass());
		assertEquals(12384, number);
		assertEquals("12384".length(), jtex.getPosition());
		
		assertEquals(23934, numberParser.readNumber(new JTEX("23934df")));
	}
	
	@Test
	public void testReadDouble() {
		String input = "23934e11 sad";
		
		NumberParser numberParser = new NumberParser();
		
		JTEX jtex = new JTEX(input);
		Object number = numberParser.readNumber(jtex);
		
		assertEquals(Double.class, number.getClass());
		assertEquals(23934e11, number);
		assertEquals("23934e11".length(), jtex.getPosition());
		
		assertEquals(0.5, numberParser.readNumber(new JTEX("+0.5")));
		assertEquals(0.5e12, numberParser.readNumber(new JTEX("+0.5e12")));
		assertEquals(12e-12, numberParser.readNumber(new JTEX("12E-12")));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException1() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readNumber(new JTEX("-a")));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException2() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readNumber(new JTEX("-12.ee2")));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException3() {
		NumberParser numberParser = new NumberParser();
		assertEquals(-21, numberParser.readNumber(new JTEX("-.e10")));
	}
}
