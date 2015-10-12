package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NullLiteral;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NullLiteralParserTest {

	@Test
	public void testReadNullValue() {
		NullLiteralParser numberParser = new NullLiteralParser();
		NullLiteral nullLiteral = numberParser.read(new JTEX("null"));
		assertEquals(0, nullLiteral.getStart());
		assertEquals(3, nullLiteral.getEnd());
		assertNull(nullLiteral.eval(null));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException1() {
		NullLiteralParser numberParser = new NullLiteralParser();
		assertNull(numberParser.read(new JTEX("nulo")));
	}
	
	@Test (expected = UnexpectedCharacterException.class)
	public void testParserException3() {
		NullLiteralParser numberParser = new NullLiteralParser();
		assertNull(numberParser.read(new JTEX("aull")));
	}
	
	@Test (expected = EndOfJtexException.class)
	public void testParserException2() {
		NullLiteralParser numberParser = new NullLiteralParser();
		assertNull(numberParser.read(new JTEX("nul")));
	}
}
