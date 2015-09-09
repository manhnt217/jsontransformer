package wazi.parser.jsontransformer.expression;

import static org.junit.Assert.*;

import org.junit.Test;

import wazi.parser.jsontransformer.expression.ExpressionParser;

public class ExpressionParserTest {
	
	@Test
	public void testSelector() {
		
		String json = 	"{\n" + 
						"  \"temp\": 12\n" + 
						"}";
		
		String expression = "J.s($.temp)";
		
		ExpressionParser expressionParser = new ExpressionParser(json);
		
		assertEquals(12, expressionParser.eval(expression));
	}
}
