package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import java.util.regex.Matcher;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.Expression;
import wazi.parser.jsontransformer.expression.jtex.JTEX;
import wazi.parser.jsontransformer.expression.parser.ExpressionParser;

public class ExpressionParserTest {
	@Test
	public void test() throws Exception {
		ExpressionParser parser = new ExpressionParser();
		Expression baseEx1 = parser.readExpression(new JTEX("10"));
		assertEquals(10, baseEx1.val());

		Expression baseEx2 = parser.readExpression(new JTEX("true"));
		assertEquals(true, baseEx2.val());

		Expression baseEx3 = parser.readExpression(new JTEX("null"));
		assertNull(baseEx3.val());

		Expression baseEx4 = parser.readExpression(new JTEX("I.add(1, 1)"));
		assertEquals(2, baseEx4.val());

		Expression baseEx5 = parser.readExpression(new JTEX("C.choice(C.ift(false, 18), C.ift(true, 27))"));
		assertEquals(27, baseEx5.val());

		Expression baseEx6 = parser.readExpression(new JTEX("C.ite(I.gt(3, 5), \"AAA\", \"BBB\")"));
		assertEquals("BBB", baseEx6.val());
	}
}
