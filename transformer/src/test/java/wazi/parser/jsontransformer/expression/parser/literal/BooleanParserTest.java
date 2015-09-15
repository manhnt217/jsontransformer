package wazi.parser.jsontransformer.expression.parser.literal;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.jtex.JTEX;
import wazi.parser.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.literal.BooleanParser;

public class BooleanParserTest {
	@Test
	public void testReadNullValue() {
		BooleanParser numberParser = new BooleanParser();
		assertTrue(numberParser.readBool(new JTEX("true")));
		assertFalse(numberParser.readBool(new JTEX("false")));
		
		assertTrue(numberParser.readBool(new JTEX("true213")));
		assertFalse(numberParser.readBool(new JTEX("falsesad")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		BooleanParser numberParser = new BooleanParser();
		assertFalse(numberParser.readBool(new JTEX("fasle")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		BooleanParser numberParser = new BooleanParser();
		assertTrue(numberParser.readBool(new JTEX("aull")));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		BooleanParser numberParser = new BooleanParser();
		assertTrue(numberParser.readBool(new JTEX("tru")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException4() {
		BooleanParser numberParser = new BooleanParser();
		assertTrue(numberParser.readBool(new JTEX("coldplay")));
	}
}
