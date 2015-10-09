package wazi.jsontransformer.expression.helper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wazi on 10/9/15.
 */
public class NumTest {

	@Test
	public void testCreate() {
		Num num = new Num("-1321");
		assertEquals(-1321, num.get());
	}
}