package wazi.jsontransformer.expression.parser.literal;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;

public class BooleanParserTest {
	@Test
	public void testReadNullValue() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.readExpression(new JTEX("true")).eval(null));
		assertEquals(false, booleanParser.readExpression(new JTEX("false")).eval(null));

		assertEquals(true, booleanParser.readExpression(new JTEX("true213")).eval(null));
		assertEquals(false, booleanParser.readExpression(new JTEX("falsesad")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(false, booleanParser.readExpression(new JTEX("fasle")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.readExpression(new JTEX("aull")).eval(null));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.readExpression(new JTEX("tru")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException4() {
		BooleanParser booleanParser = new BooleanParser();
		assertEquals(true, booleanParser.readExpression(new JTEX("coldplay")).eval(null));
	}
}
