package wazi.parser.jsontransformer.expression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wazi on 2015-09-08 008.
 */
public class LiteralValueTest {

	@Test
	public void testParseLiteral() {
		assertEquals(true, LiteralValue.parseLiteral("true"));
		assertEquals(false, LiteralValue.parseLiteral("false"));
		assertEquals(18, LiteralValue.parseLiteral("+18"));
		assertEquals(-211, LiteralValue.parseLiteral("-211"));
		assertEquals(10000000000000000000000d, LiteralValue.parseLiteral("10000000000000000000000"));
		assertEquals("Hello World", LiteralValue.parseLiteral("\"Hello World\""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed1() {
		LiteralValue.parseLiteral("Hello World\"");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed2() {
		LiteralValue.parseLiteral("\"Hello World");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed3() {
		LiteralValue.parseLiteral("34sdfa5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed4() {
		LiteralValue.parseLiteral("Nulls");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseLiteralFailed5() {
		LiteralValue.parseLiteral("\"False");
	}
}