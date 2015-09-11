package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

public class NullParserTest {

	@Test
	public void testReadNullValue() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.readNullValue(new JTEX("null")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.readNullValue(new JTEX("nulo")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.readNullValue(new JTEX("aull")));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.readNullValue(new JTEX("nul")));
	}
}
