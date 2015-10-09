package wazi.jsontransformer.expression.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.literal.SymbolParser;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by wazi on 2015-09-19 019.
 */
public class SymbolTest {

	@Test
	public void testApply() throws Exception {
		SymbolParser parser = new SymbolParser();
		BaseExpression symbolExpression = parser.read(new JTEX("#a+14"));
		assertEquals(15, symbolExpression.eval(new HashMap<String, Object>() {
			{
				put("#a", 15);
			}
		}));
	}
}