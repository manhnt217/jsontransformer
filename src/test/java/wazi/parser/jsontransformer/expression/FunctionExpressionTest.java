package wazi.parser.jsontransformer.expression;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.FunctionExpression.ReflectionUtil;


public class FunctionExpressionTest {

	@Test
	public void testSimple() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(0.5, ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.D", "div", new Integer(4), new Integer(8)));
		assertEquals("abc48x", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.I", "dummy", "abc", 4, 8, "x"));
		assertEquals(2, ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.I", "div", 9, 4));
		assertEquals("Hello", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.S", "concat", "Hell", "o"));
		assertEquals("null8", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.S", "concat", null, 8));
		assertEquals("truenull", ReflectionUtil.invoke("wazi.parser.jsontransformer.expression.function.S", "concat", true, null));
	}

}
