package wazi.jsontransformer.parser;

import org.junit.Test;
import wazi.jsontransformer.expression.FunctionExpression;
import wazi.jsontransformer.expression.FunctionExpression.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class FunctionParserTest {

	@Test
	public void testReadFunction() {

//		ExpressionParser exParser = new ExpressionParser();
//		FunctionParser parser = new FunctionParser(exParser);
//		FunctionExpression funcEx = parser.read(new JTEX("I.add( I.add(4, 5), 23, 2)"));
//		assertEqualsFunction(funcEx, exParser.packagePrefixString + "I", "add", 1);
//		assertEquals(3, funcEx.getArguments().size());
	}

	@Test
	public void testReadFunction2() {

//		ExpressionParser exParser = new ExpressionParser();
//		FunctionParser parser = new FunctionParser(exParser);
//		FunctionExpression funcEx = parser
//				.read(new JTEX("C.choice(C.ift(I.gt(3, 5), \"3 > 5\"), C.ift(I.lt(10, 5), \"10 < 5\"), C.ift(true, \"Default case\"))"));
//		assertEqualsFunction(funcEx, exParser.packagePrefixString + "C", "choice", 1);
//		assertEquals(3, funcEx.getArguments().size());
	}

	@Test
	public void testEvaluateSimpleFunction() throws Exception {

//		ExpressionParser exParser = new ExpressionParser();
//		FunctionParser funcParser = new FunctionParser(exParser);
//		FunctionExpression funcEx = funcParser.read(new JTEX("S.concat(\"a\", \"b\", null, \"c\")"));
//		assertEquals("abc", funcEx.eval());
	}

	@Test
	public void testEvaluateChoiceFunction() throws Exception {

//		ExpressionParser exParser = new ExpressionParser();
//		FunctionParser funcParser = new FunctionParser(exParser);
//		FunctionExpression funcEx = funcParser
//				.read(new JTEX("C.choice(C.ift(I.gt(3, 5), \"3 > 5\"), C.ift(I.lt(10, 5), \"10 < 5\"), C.ift(true, \"Default case\"))"));//notice I.lt(10, 5)
//		assertEquals("Default case", funcEx.eval());
//
//		FunctionExpression funcEx2 = funcParser
//				.read(new JTEX("C.choice(C.ift(I.gt(3, 5), \"3 > 5\"), C.ift(I.gt(10, 5), \"10 > 5\"), C.ift(true, \"Default case\"))"));//notice I.gt(10, 5)
//		assertEquals("10 > 5", funcEx2.eval());
	}

	@Test
	public void testEvaluateJTEXFunction() throws Exception {

//		String inputJSONString = "{\"status\":\"OK\"}";
//
//		ExpressionParser exParser = new ExpressionParser();
//		exParser.setInputJSON(Configuration.defaultConfiguration().jsonProvider().parse(inputJSONString));
//
//		FunctionParser funcParser = new FunctionParser(exParser);
//		FunctionExpression funcEx = funcParser.read(new JTEX("J.p(\"$.status\")"));
//		assertTrue(funcEx instanceof JsonPathExpression);
//		assertEquals("OK", funcEx.eval());
	}

	@Test
	public void testFailed1() {

//		try {
//			ExpressionParser exParser = new ExpressionParser();
//			FunctionParser parser = new FunctionParser(exParser);
//			FunctionExpression funcEx = parser.read(new JTEX("kI.add(1, 2)"));
//			assertEqualsFunction(funcEx, "I", "add", 1);
//			fail("Should not got here");
//		} catch (UnexpectedCharacterException e) {
//			assertEquals(1, e.getPosition());
//		}
	}

	@Test
	public void testFailed2() {

//		try {
//			ExpressionParser exParser = new ExpressionParser();
//			FunctionParser parser = new FunctionParser(exParser);
//			FunctionExpression funcEx = parser.read(new JTEX("Idf_rr.add(1,  "));
//			assertEqualsFunction(funcEx, "I", "add", 1);
//		} catch (EndOfJtexException e) {
//			assertEquals(16, e.getPosition());
//		}
	}

	@Test
	public void testReflectionSimple()
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		assertEquals(0.5, ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.D", "div", 4.0, 8.0));
		assertEquals(2, ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.I", "div", 9, 4));
		assertEquals("Hello", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.S", "concat", "Hell", "o"));
		assertEquals("8", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.S", "concat", null, 8));
		assertEquals("true", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.S", "concat", true, null));
	}

	@Test
	public void testReflectionVarargMethod()
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(10, ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.I", "add", 1, 2, 3, 4));
		assertEquals("xyz10.0", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.function.I", "dummy", "xyz", 1.0, 2.0, 3.0, 4.0));
	}

	private void assertEqualsFunction(FunctionExpression funcEx, String className, String methodName, int position) {

		assertEquals(className, funcEx.getClassName());
		assertEquals(methodName, funcEx.getMethodName());
		assertEquals(position, funcEx.getStart());
	}
}
