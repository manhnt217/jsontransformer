package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.regex.Matcher;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.parser.ExpressionParser;

public class ExpressionParserTest {

	@Test
	public void testEvalSelector() {

		String json = "{\n" 
					+ "  \"temp\": 12\n" 
					+ "}";

		String expression = "$.temp";

		ExpressionParser expressionParser = new ExpressionParser(json);

		assertEquals(12, expressionParser.eval(expression));
	}

	@Test
	public void testEvalFunction() {

		String json = "{\n" 
					+ "  \"temp\": 12\n" 
					+ "}";

		String expression = "D.add(J.s($.temp), 15, J.s($.temp), 443)";

		ExpressionParser expressionParser = new ExpressionParser(json);

		assertEquals(27, expressionParser.eval(expression));
	}

	@Test
	public void testExtractFunctionCall() {

		String json = "{\n" 
					+ "  \"temp\": 12\n" 
					+ "}";

		String function = "D.add($.temp, 15)";

		ExpressionParser expressionParser = new ExpressionParser(json);

		Matcher matcher = ExpressionParser.PATTERN_FUNCTION.matcher(function);
		ExpressionParser.FunctionCall functionCall = expressionParser.extractFunctionCall(matcher);

		assertEquals("D", functionCall.className);
		assertEquals("add", functionCall.methodNameString);
		assertTrue(functionCall.args.contains("$.temp"));
		assertTrue(functionCall.args.contains("15"));
	}
}
