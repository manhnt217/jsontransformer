package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

import static org.junit.Assert.assertEquals;

public class BooleanExpressionParserTest {
	@Test
	public void testReadNullValue() {
		BooleanExpressionParser booleanExpressionParser = new BooleanExpressionParser();
		assertEquals(true, booleanExpressionParser.read(new JTEX("true")).eval(null));
		assertEquals(false, booleanExpressionParser.read(new JTEX("false")).eval(null));

		assertEquals(true, booleanExpressionParser.read(new JTEX("true213")).eval(null));
		assertEquals(false, booleanExpressionParser.read(new JTEX("falsesad")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		BooleanExpressionParser booleanExpressionParser = new BooleanExpressionParser();
		assertEquals(false, booleanExpressionParser.read(new JTEX("fasle")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		BooleanExpressionParser booleanExpressionParser = new BooleanExpressionParser();
		assertEquals(true, booleanExpressionParser.read(new JTEX("aull")).eval(null));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		BooleanExpressionParser booleanExpressionParser = new BooleanExpressionParser();
		assertEquals(true, booleanExpressionParser.read(new JTEX("tru")).eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException4() {
		BooleanExpressionParser booleanExpressionParser = new BooleanExpressionParser();
		assertEquals(true, booleanExpressionParser.read(new JTEX("coldplay")).eval(null));
	}
}
