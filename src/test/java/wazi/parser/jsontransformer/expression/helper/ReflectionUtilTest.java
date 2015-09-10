package wazi.parser.jsontransformer.expression.helper;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;


public class ReflectionUtilTest {

	@Test
	public void testSimple() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(0.5, ReflectionUtil.invokeStaticMethod("wazi.parser.jsontransformer.expression.function.D", "div", 4, 8));
		assertEquals(12, ReflectionUtil.invokeStaticMethod("wazi.parser.jsontransformer.expression.function.I", "add", 4, 8));
		assertEquals(2, ReflectionUtil.invokeStaticMethod("wazi.parser.jsontransformer.expression.function.I", "div", 9, 4));
		assertEquals("Hello", ReflectionUtil.invokeStaticMethod("wazi.parser.jsontransformer.expression.function.S", "concat", "Hell", "o"));
	}

}
