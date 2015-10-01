package wazi.jsontransformer.parser.literal;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.exception.EndOfJtexException;
import wazi.jsontransformer.parser.exception.UnexpectedCharacterException;

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
