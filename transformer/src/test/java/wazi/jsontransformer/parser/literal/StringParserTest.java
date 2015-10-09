package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

import static org.junit.Assert.assertEquals;

public class StringParserTest {

	@Test
	public void testReadString() {
		StringParser parser = new StringParser();
		JTEX jtex = new JTEX("\"Hello World\"F#ds");
		assertEquals("Hello World", parser.read(jtex).eval(null));
		assertEquals('F', (char) jtex.retrieveNext());
		
		//a regular expression I've found in my source code :D
		assertEquals("\"((\\\\[tbnrf'\"\\\\])|[^\\\\]|(\\\\u[a-fA-F0-9]{4}))*?\"", parser.read(new JTEX("\"\\\"((\\\\\\\\[tbnrf'\\\"\\\\\\\\])|[^\\\\\\\\]|(\\\\\\\\u[a-fA-F0-9]{4}))*?\\\"\"")).eval(null));
	}
	
	@Test
	public void testReadUnicodeChar() {
		StringParser parser = new StringParser();
		assertEquals(0x7a0f, (int) parser.readUnicodeCharacter(new JTEX("7a0F")));
	}
	
	@Test
	public void testReadChar() {
		StringParser parser = new StringParser();
		JTEX jtex = new JTEX("\\u3a9fXmd");
		assertEquals('㪟', (int) parser.readChar(jtex));
		assertEquals('X', (char) jtex.retrieveNext());
		
		assertEquals('\u3a9f', (int) parser.readChar(new JTEX("\\u3A9f")));
		assertEquals('関', (int) parser.readChar(new JTEX("関")));
	}
	
	@Test(expected = UnexpectedCharacterException.class)
	public void testReadStringFailed1() {
		StringParser parser = new StringParser();
		parser.read(new JTEX("\"\\kMD\""));//"\kMD"
	}
	
	@Test(expected = UnexpectedCharacterException.class)
	public void testReadStringFailed2() {
		StringParser parser = new StringParser();
		parser.read(new JTEX("\"String \\u19H6\""));//"String \ u19H6" without space between \ and u
	}
	
	@Test(expected = EndOfJtexException.class)
	public void testReadStringFailed3() {
		StringParser parser = new StringParser();
		parser.read(new JTEX("\"Hello"));//"Hello
	}
}
