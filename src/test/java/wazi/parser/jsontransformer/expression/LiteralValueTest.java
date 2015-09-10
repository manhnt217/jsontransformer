package wazi.parser.jsontransformer.expression;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.function.LiteralValueParser;

import static org.junit.Assert.*;

/**
 * Created by wazi on 2015-09-08 008.
 */
public class LiteralValueTest {

	@Test
	public void testParseLiteral() {
		assertEquals(true, LiteralValueParser.parseLiteral("true"));
		assertEquals(false, LiteralValueParser.parseLiteral("false"));
		assertEquals(18, LiteralValueParser.parseLiteral("+18"));
		assertEquals(-211, LiteralValueParser.parseLiteral("-211"));
		assertEquals(10000000000000000000000d, LiteralValueParser.parseLiteral("10000000000000000000000"));
		assertEquals("Hello World", LiteralValueParser.parseLiteral("\"Hello World\""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed1() {
		LiteralValueParser.parseLiteral("Hello World\"");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed2() {
		LiteralValueParser.parseLiteral("\"Hello World");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed3() {
		LiteralValueParser.parseLiteral("34sdfa5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed4() {
		LiteralValueParser.parseLiteral("Nulls");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed5() {
		LiteralValueParser.parseLiteral("\"False");
	}
}