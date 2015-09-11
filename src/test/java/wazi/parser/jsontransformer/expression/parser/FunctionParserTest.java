package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.FunctionExpression;
import wazi.parser.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.parser.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.parser.jsontransformer.expression.parser.jtex.JTEX;


public class FunctionParserTest {
	
	@Test
	public void testReadFunction() {
		
		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser.readFunction(new JTEX("I.add ( 23, \"ad24\",  I.add(1, 2)  )"));
		assertFunction(funcEx, "I", "add", 1);
	}

	@Test(expected = UnexpectedCharacterException.class)
	public void testFailed1() {

		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser.readFunction(new JTEX("kI.add(1, 2)"));
		assertFunction(funcEx, "I", "add", 1);
	}
	
	@Test(expected = EndOfJtexException.class)
	public void testFailed2() {
		
		ExpressionParser exParser = new ExpressionParser();
		FunctionParser parser = new FunctionParser(exParser);
		FunctionExpression funcEx = parser.readFunction(new JTEX("Idf_rr.add(1,  "));
		assertFunction(funcEx, "I", "add", 1);
	}

	private void assertFunction(FunctionExpression funcEx, String className, String methodName, int position) {

		assertEquals(className, funcEx.getClassName());
		assertEquals(methodName, funcEx.getMethodName());
		assertEquals(position, funcEx.getPosition());
	}
}
