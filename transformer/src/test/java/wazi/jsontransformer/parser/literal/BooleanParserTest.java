package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

import static org.junit.Assert.assertEquals;

public class BooleanParserTest {
	@Test
	public void testReadNullValue() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.read(new JTEX("true")).eval(null));
		assertEquals(false, booleanParser.read(new JTEX("false")).eval(null));

		assertEquals(true, booleanParser.read(new JTEX("true213")).eval(null));
		assertEquals(false, booleanParser.read(new JTEX("falsesad")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(false, booleanParser.read(new JTEX("fasle")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.read(new JTEX("aull")).eval(null));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.read(new JTEX("tru")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException4() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.read(new JTEX("coldplay")).eval(null));
	}
}
