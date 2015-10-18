package wazi.jsontransformer.parser.jtex;

import org.junit.Test;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.expression.jtex.JTEX;

import static org.junit.Assert.*;

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
