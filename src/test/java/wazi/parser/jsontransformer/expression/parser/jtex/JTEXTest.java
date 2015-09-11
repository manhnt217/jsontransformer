package wazi.parser.jsontransformer.expression.parser.jtex;

import static org.junit.Assert.*;

import org.junit.Test;

public class JTEXTest {

	@Test
	public void test() throws EndOfJtexException {

		JTEX jtex = new JTEX("ありがとう");

		assertTrue(jtex.hasNext());

		assertTrue('あ' == jtex.next());
		assertTrue('り' == jtex.next());
		assertTrue('が' == jtex.next());
		assertTrue('と' == jtex.next());
		assertTrue('う' == jtex.next());

		assertFalse(jtex.hasNext());
	}

	@Test(expected = EndOfJtexException.class)
	public void testEndOfJtexException() throws EndOfJtexException {

		JTEX jtex = new JTEX("");
		assertNull(jtex.next());
	}
}
