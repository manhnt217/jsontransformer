package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.exception.parser.ParserException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NumberLiteral;

import static org.junit.Assert.assertEquals;

public class NumberLiteralParserTest {

	@Test
	public void testReadInteger() {
		NumberLiteralParser numberLiteralParser = new NumberLiteralParser();
		
		JTEX jtex = new JTEX("12384xyf");
		BaseExpression number = numberLiteralParser.read(jtex);
		assertEquals(NumberLiteral.class, number.getClass());
		assertEquals(12384, number.eval(null));
		assertEquals("12384".length(), jtex.getNextPosition());
		
		assertEquals(23934, numberLiteralParser.read(new JTEX("23934df")).eval(null));
	}
	
	@Test
	public void testReadDouble() {
		String input = "23934e11 sad";
		
		NumberLiteralParser numberLiteralParser = new NumberLiteralParser();
		
		JTEX jtex = new JTEX(input);
        BaseExpression number = numberLiteralParser.read(jtex);
		
		assertEquals(NumberLiteral.class, number.getClass());
		assertEquals(23934e11, number.eval(null));
		assertEquals("23934e11".length(), jtex.getNextPosition());
		
		assertEquals(0.5, numberLiteralParser.read(new JTEX("+0.5")).eval(null));
		assertEquals(0.5e12, numberLiteralParser.read(new JTEX("+0.5e12")).eval(null));
		assertEquals(12e-12, numberLiteralParser.read(new JTEX("12E-12")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException1() {
		NumberLiteralParser numberLiteralParser = new NumberLiteralParser();
		assertEquals(-21, numberLiteralParser.read(new JTEX("-a")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException2() {
		NumberLiteralParser numberLiteralParser = new NumberLiteralParser();
		assertEquals(-21, numberLiteralParser.read(new JTEX("-12.ee2")).eval(null));
	}
	
	@Test(expected = ParserException.class)
	public void testParseException3() {
		NumberLiteralParser numberLiteralParser = new NumberLiteralParser();
		assertEquals(-21, numberLiteralParser.read(new JTEX("-.e10")).eval(null));
	}
}
