package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.FunctionExpression;
import wazi.parser.jsontransformer.expression.FunctionExpression.ReflectionUtil;
import wazi.parser.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;

public class FunctionParserTest {

	@Test
	public void testReadFunction() {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser.readFunction(new JTEX("I.add( I.add(4, 5), 23, 2)"));
		assertEqualsFunction(funcEx, exParser.packagePrefixString + "I", "add", 1);
		assertEquals(3, funcEx.getArguments().size());
	}

	@Test
	public void testReadFunction2() {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser
				.readFunction(new JTEX("C.choice(C.ift(I.gt(3, 5), \"3 > 5\"), C.ift(I.lt(10, 5), \"10 < 5\"), C.ift(true, \"Default case\"))"));
		assertEqualsFunction(funcEx, exParser.packagePrefixString + "C", "choice", 1);
		assertEquals(3, funcEx.getArguments().size());
	}

	@Test
	public void testEvaluateSimpleFunction() throws Exception {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser funcParser = new FunctionParser(exParser);
		FunctionExpression funcEx = funcParser.readFunction(new JTEX("S.concat(\"a\", \"b\", null, \"c\")"));
		assertEquals("abc", funcEx.val());
	}

	@Test
	public void testEvaluateChoiceFunction() throws Exception {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser funcParser = new FunctionParser(exParser);
		FunctionExpression funcEx = funcParser
				.readFunction(new JTEX("C.choice(C.ift(I.gt(3, 5), \"3 > 5\"), C.ift(I.lt(10, 5), \"10 < 5\"), C.ift(true, \"Default case\"))"));//notice I.lt(10, 5)
		assertEquals("Default case", funcEx.val());

		FunctionExpression funcEx2 = funcParser
				.readFunction(new JTEX("C.choice(C.ift(I.gt(3, 5), \"3 > 5\"), C.ift(I.gt(10, 5), \"10 > 5\"), C.ift(true, \"Default case\"))"));//notice I.gt(10, 5)
		assertEquals("10 > 5", funcEx2.val());
	}

	@Test(expected = UnexpectedCharacterException.class)
	public void testFailed1() {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser.readFunction(new JTEX("kI.add(1, 2)"));
		assertEqualsFunction(funcEx, "I", "add", 1);
	}

	@Test(expected = EndOfJtexException.class)
	public void testFailed2() {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser.readFunction(new JTEX("Idf_rr.add(1,  "));
		assertEqualsFunction(funcEx, "I", "add", 1);
	}

	@Test
	public void testReflectionSimple()
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		assertEquals(0.5, ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.D", "div", new Integer(4), new Integer(8)));
		assertEquals(2, ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.I", "div", 9, 4));
		assertEquals("Hello", ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.S", "concat", "Hell", "o"));
		assertEquals("8", ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.S", "concat", null, 8));
		assertEquals("true", ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.S", "concat", true, null));
	}

	@Test
	public void testReflectionVarargMethod()
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(10, ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.I", "add", 1, 2, 3, 4));
		assertEquals("xyz10.0", ReflectionUtil.invokeStatic("wazi.parser.jsontransformer.expression.function.I", "dummy", "xyz", 1.0, 2.0, 3.0, 4.0));
	}

	private void assertEqualsFunction(FunctionExpression funcEx, String className, String methodName, int position) {

		assertEquals(className, funcEx.getClassName());
		assertEquals(methodName, funcEx.getMethodName());
		assertEquals(position, funcEx.getPosition());
	}
}
