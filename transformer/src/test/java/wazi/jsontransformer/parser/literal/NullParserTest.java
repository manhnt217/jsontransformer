package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NullExpression;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NullParserTest {

	@Test
	public void testReadNullValue() {
		NullParser numberParser = new NullParser();
		NullExpression nullExpression = numberParser.read(new JTEX("null"));
		assertEquals(0, nullExpression.getStart());
		assertEquals(3, nullExpression.getEnd());
		assertNull(nullExpression.eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.read(new JTEX("nulo")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.read(new JTEX("aull")));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		NullParser numberParser = new NullParser();
		assertNull(numberParser.read(new JTEX("nul")));
	}
}
