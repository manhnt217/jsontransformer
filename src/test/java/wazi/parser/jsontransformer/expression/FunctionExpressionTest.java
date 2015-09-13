package wazi.parser.jsontransformer.expression;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.FunctionExpression.ReflectionUtil;


public class FunctionExpressionTest {

	@Test
	public void testSimple() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		assertEquals(0.5, ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.D", "div", new Integer(4), new Integer(8)));
		assertEquals(2, ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.I", "div", 9, 4));
		assertEquals("Hello", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.S", "concat", "Hell", "o"));
		assertEquals("null8", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.S", "concat", null, 8));
		assertEquals("truenull", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.S", "concat", true, null));
	}

	@Test
	public void testVarargMethod() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(10, ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.I", "add", 1, 2, 3, 4));
		assertEquals("xyz10.0", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.I", "dummy", "xyz", 1.0, 2.0, 3.0, 4.0));
	}
}
