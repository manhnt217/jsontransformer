package wazi.jsontransformer.expression.jtex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.exception.EndOfJtexException;

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
