package wazi.jsontransformer.parser.arithmetic;

import junit.framework.TestCase;
import org.junit.Test;
import wazi.jsontransformer.expression.arithmetic.ArithmeticExpression;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.arithmetic.FactorExpression;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.jtex.JTEX;

import wazi.jsontransformer.expression.arithmetic.TermExpression;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by wazi on 2015-10-04 004.
 */
public class ArithmeticExpressionParserTest extends TestCase {

	@Test
	public void testParseExpression() {
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser();
		ArithmeticExpression expression = parser.read(new JTEX("- 1 <> 2 * 3"));
		assertEquals(1, expression.terms.size());
		assertEquals(1, expression.terms.get(0).factors.size());
	}

	@Test
	public void testEvaluateExpression() {
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser();
		BaseExpression expression = parser.read(new JTEX("- (- 1 - 2) * 3"));
		assertEquals(9, expression.eval(null));
	}

	@Test
	public void testEvaluateExpression2() {
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser();
		BaseExpression expression = parser.read(new JTEX("64 / (10 - 2)"));
		assertEquals(0, expression.getStart());
		assertEquals(12, expression.getEnd());
		assertEquals(8, expression.eval(null));
	}

	@Test
	public void testEvaluateExpression3() {
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser();
		BaseExpression expression = parser.read(new JTEX("(- 6 + 9 div 4) * - (5 -11)"));
		assertEquals(0, expression.getStart());
		assertEquals(26, expression.getEnd());
		assertEquals(-24, expression.eval(null));
	}

	@Test
	public void testEvaluateExpression4() {
		ArithmeticExpressionParser parser = new ArithmeticExpressionParser();
		BaseExpression expression = parser.read(new JTEX("#_d*(5-#abc)"));
		assertEquals(0, expression.getStart());
		assertEquals(11, expression.getEnd());
		assertEquals(-12, expression.eval(new HashMap<String, Object>() {{
			put("#abc", new Num(11));
			put("#_d", new Num(2));
		}}));
	}

	@Test
	public void testFactorParser() {
		ArithmeticExpressionParser arithmeticExpressionParser = new ArithmeticExpressionParser();
		FactorExpressionParser factorParser = arithmeticExpressionParser.factorParser;
		BaseExpression expression = factorParser.read(new JTEX("-123"));
		assertTrue(expression instanceof FactorExpression);
		FactorExpression factorExpression = (FactorExpression) expression;
		assertEquals(-123, ((Num) factorExpression.eval(null)).get());
	}

	@Test
	public void testTermParser() {
		ArithmeticExpressionParser arithmeticExpressionParser = new ArithmeticExpressionParser();
		TermExpressionParser termParser = arithmeticExpressionParser.termParser;
		JTEX jtex = new JTEX("1*2/3");
		TermExpression termExpression = termParser.read(jtex);
		assertEquals(0, termExpression.getStart());
		assertEquals(4, termExpression.getEnd());
		assertEquals(2.0 / 3, termExpression.eval(null));
	}
}