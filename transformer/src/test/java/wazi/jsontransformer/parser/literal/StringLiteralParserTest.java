package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;

import static org.junit.Assert.assertEquals;

public class StringLiteralParserTest {

	@Test
	public void testReadString() {
		StringLiteralParser parser = new StringLiteralParser();
		JTEX jtex = new JTEX("'Hello World, \\'Wazi\\''F#ds");
		assertEquals("Hello World, 'Wazi'", parser.read(jtex).eval(null));
		assertEquals('F', (char) jtex.retrieveNext());

		//a regular expression I've found in my source code :D
		//REGEX = /'((\\[tbnrf'\\])|[^\\]|(\\u[a-fA-F0-9]{4}))*?'/
		assertEquals("'((\\\\[tbnrf'\\\\])|[^\\\\]|(\\\\u[a-fA-F0-9]{4}))*?'", parser.read(new JTEX("'\\'((\\\\\\\\[tbnrf\\'\\\\\\\\])|[^\\\\\\\\]|(\\\\\\\\u[a-fA-F0-9]{4}))*?\\''")).eval(null));
	}
	
	@Test
	public void testReadUnicodeChar() {
		StringLiteralParser parser = new StringLiteralParser();
		assertEquals(0x7a0f, (int) parser.readUnicodeCharacter(new JTEX("7a0F")));
	}
	
	@Test
	public void testReadChar() {
		StringLiteralParser parser = new StringLiteralParser();
		JTEX jtex = new JTEX("\\u3a9fXmd");
		assertEquals('㪟', (int) parser.readChar(jtex));
		assertEquals('X', (char) jtex.retrieveNext());
		
		assertEquals('\u3a9f', (int) parser.readChar(new JTEX("\\u3A9f")));
		assertEquals('関', (int) parser.readChar(new JTEX("関")));
	}
	
	@Test(expected = UnexpectedCharacterException.class)
	public void testReadStringFailed1() {
		StringLiteralParser parser = new StringLiteralParser();
		parser.read(new JTEX("'\\kMD'"));//"\kMD"
	}
	
	@Test(expected = UnexpectedCharacterException.class)
	public void testReadStringFailed2() {
		StringLiteralParser parser = new StringLiteralParser();
		parser.read(new JTEX("'String \\u19H6'"));//"String \ u19H6" without space between \ and u
	}
	
	@Test(expected = EndOfJtexException.class)
	public void testReadStringFailed3() {
		StringLiteralParser parser = new StringLiteralParser();
		parser.read(new JTEX("'Hello"));//"Hello
	}
}
