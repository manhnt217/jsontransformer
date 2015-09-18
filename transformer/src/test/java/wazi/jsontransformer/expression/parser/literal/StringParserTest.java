package wazi.jsontransformer.expression.parser.literal;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.jsontransformer.expression.parser.literal.StringParser;

public class StringParserTest {

	@Test
	public void testReadString() {
		StringParser parser = new StringParser();
		JTEX jtex = new JTEX("\"Hello World\"F#ds");
		assertEquals("Hello World", parser.readString(jtex));
		assertEquals('F', (char) jtex.retrieveNext());
		
		//a regular expression I've found in my source code :D
		assertEquals("\"((\\\\[tbnrf'\"\\\\])|[^\\\\]|(\\\\u[a-fA-F0-9]{4}))*?\"", parser.readString(new JTEX("\"\\\"((\\\\\\\\[tbnrf'\\\"\\\\\\\\])|[^\\\\\\\\]|(\\\\\\\\u[a-fA-F0-9]{4}))*?\\\"\"")));
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
		parser.readString(new JTEX("\"\\kMD\""));//"\kMD"
	}
	
	@Test(expected = UnexpectedCharacterException.class)
	public void testReadStringFailed2() {
		StringParser parser = new StringParser();
		parser.readString(new JTEX("\"String \\u19H6\""));//"String \ u19H6" without space between \ and u
	}
	
	@Test(expected = EndOfJtexException.class)
	public void testReadStringFailed3() {
		StringParser parser = new StringParser();
		parser.readString(new JTEX("\"Hello"));//"Hello
	}
}
