package wazi.jsontransformer.parser.helper;

import org.junit.Test;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.operator.Operator;

import static org.junit.Assert.*;

/**
 * Created by wazi on 2015-10-08 008.
 */
public class NumTest {

	@Test
	public void testNumber() {
		Num n1 = new Num(11);
		Num n2 = new Num(4);
		assertEquals(15, n1.apply(Operator.Op.PLUS, n2).get());
	}

	@Test
	public void testNumber2() {
		Num n1 = new Num(11);
		Num n2 = new Num(4);
		assertEquals(11.0/4, n1.apply(Operator.Op.DIVIDE, n2).get());
	}

	@Test
	public void testNumber3() {
		Num n1 = new Num(-11);
		Num n2 = new Num(4);
		assertEquals(-2, n1.apply(Operator.Op.DIV, n2).get());
	}


	@Test
	public void testNumber4() {
		Num n1 = new Num(8.5);
		Num n2 = new Num(1.6);
		assertEquals(8.5 % 1.6, n1.apply(Operator.Op.MOD, n2).get());
	}

	@Test
	public void testNumberCoversion() {
		Num n1 = new Num(111111111111l);
		assertTrue(n1.get() instanceof Double);
		assertEquals(111111111111.0, n1.get());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNumberExeException() {
		Num n1 = new Num(-3);
		Num n2 = new Num(4);
		assertEquals(0, n1.apply(Operator.Op.AND, n2).get());
		fail("Should throw exception");
	}
}