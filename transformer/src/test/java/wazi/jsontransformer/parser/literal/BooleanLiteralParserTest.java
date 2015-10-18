package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.jtex.JTEX;

import static org.junit.Assert.assertEquals;

public class BooleanLiteralParserTest {
	@Test
	public void testReadNullValue() {
		BooleanLiteralParser booleanLiteralParser = new BooleanLiteralParser();
		assertEquals(true, booleanLiteralParser.read(new JTEX("true")).eval(null));
		assertEquals(false, booleanLiteralParser.read(new JTEX("false")).eval(null));

		assertEquals(true, booleanLiteralParser.read(new JTEX("true213")).eval(null));
		assertEquals(false, booleanLiteralParser.read(new JTEX("falsesad")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		BooleanLiteralParser booleanLiteralParser = new BooleanLiteralParser();
		assertEquals(false, booleanLiteralParser.read(new JTEX("fasle")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		BooleanLiteralParser booleanLiteralParser = new BooleanLiteralParser();
		assertEquals(true, booleanLiteralParser.read(new JTEX("aull")).eval(null));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		BooleanLiteralParser booleanLiteralParser = new BooleanLiteralParser();
		assertEquals(true, booleanLiteralParser.read(new JTEX("tru")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException4() {
		BooleanLiteralParser booleanLiteralParser = new BooleanLiteralParser();
		assertEquals(true, booleanLiteralParser.read(new JTEX("coldplay")).eval(null));
	}
}
