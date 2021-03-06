package wazi.jsontransformer.parser;

import com.jayway.jsonpath.Configuration;
import org.junit.Test;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.UnexpectedCharacterException;
import wazi.jsontransformer.expression.FunctionExpression;
import wazi.jsontransformer.expression.FunctionExpression.ReflectionUtil;
import wazi.jsontransformer.expression.jtex.JTEX;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FunctionExpressionParserTest {

	@Test
	public void testReadFunction() {

		ExpressionParser exParser = new ExpressionParser();
		FunctionExpressionParser parser = exParser.functionExpressionParser;
		FunctionExpression funcEx = parser.read(new JTEX("I.add( I.add(4, 5), 23, 2)"));
		assertEqualsFunction(funcEx, FunctionExpression.DEFAULT_FUNCTION_PACKAGE + "I", "add", 0);
		assertEquals(3, funcEx.getArguments().size());
	}

	@Test
	public void testEvaluateSimpleFunction() throws Exception {

		ExpressionParser exParser = new ExpressionParser();
		FunctionExpressionParser parser = exParser.functionExpressionParser;
		FunctionExpression funcEx = parser.read(new JTEX("concat(1 + 2, 'adf')"));
		assertEquals("3adf", funcEx.eval(null));
	}

	@Test
	public void testEvaluateChoiceFunction() throws Exception {

		ExpressionParser exParser = new ExpressionParser();
		FunctionExpressionParser parser = exParser.functionExpressionParser;
		FunctionExpression funcEx = parser
				.read(new JTEX("C.choice(C.ift(I.gt(3, 5), '3 > 5'), C.ift(I.lt(10, 5), '10 < 5'), C.ift(true, 'Default case'))"));//notice I.lt(10, 5)
		assertEquals("Default case", funcEx.eval(null));

		FunctionExpression funcEx2 = parser
				.read(new JTEX("C.choice(C.ift(I.gt(3, 5), '3 > 5'), C.ift(I.gt(10, 5), '10 > 5'), C.ift(true, 'Default case'))"));//notice I.gt(10, 5)
		assertEquals("10 > 5", funcEx2.eval(null));
	}

	@Test
	public void testEvaluateJSONPathFunction() throws Exception {

		String inputJSONString = "{\"status\":\"OK\"}";
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(inputJSONString);

		ExpressionParser exParser = new ExpressionParser();
		FunctionExpressionParser parser = exParser.functionExpressionParser;
		FunctionExpression funcEx = parser.read(new JTEX("concat(2, p('$.status'))"));
		assertEquals("2OK", funcEx.eval(
				new HashMap<String, Object>() {{
					put(FunctionExpression.SRC_JSON_SYMBOL, document);
				}}
		));
	}

	@Test
	public void testFailed1() {

		try {
			ExpressionParser exParser = new ExpressionParser();
			FunctionExpressionParser parser = exParser.functionExpressionParser;
			FunctionExpression funcEx = parser.read(new JTEX("kI.add(1, 2)"));
			assertEqualsFunction(funcEx, "I", "add", 1);
			fail("Should not got here");
		} catch (UnexpectedCharacterException e) {
			assertEquals(3, e.getPosition());
		}
	}



	@Test
	public void testFailed2() {

		try {
			ExpressionParser exParser = new ExpressionParser();
			FunctionExpressionParser parser = exParser.functionExpressionParser;
			FunctionExpression funcEx = parser.read(new JTEX("Idf_rr.add(1,  "));
			assertEqualsFunction(funcEx, "I", "add", 1);
		} catch (EndOfJtexException e) {
			assertEquals(15, e.getPosition());
		}
	}

	@Test
	public void testReflectionSimple()
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		assertEquals(0.5, ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.D", "div", 4.0, 8.0));
		assertEquals(2, ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.I", "div", 9, 4));
		assertEquals("Hello", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.S", "concat", "Hell", "o"));
		assertEquals("8", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.S", "concat", null, 8));
		assertEquals("true", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.S", "concat", true, null));
	}

	@Test
	public void testReflectionVarargMethod()
			throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		assertEquals(10, ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.I", "add", 1, 2, 3, 4));
		assertEquals("xyz10.0", ReflectionUtil.invokeStatic("wazi.jsontransformer.expression.helper.function.I", "dummy", "xyz", 1.0, 2.0, 3.0, 4.0));
	}

	private void assertEqualsFunction(FunctionExpression funcEx, String className, String methodName, int position) {

		assertEquals(className, funcEx.getClassName());
		assertEquals(methodName, funcEx.getMethodName());
		assertEquals(position, funcEx.getStart());
	}
}
