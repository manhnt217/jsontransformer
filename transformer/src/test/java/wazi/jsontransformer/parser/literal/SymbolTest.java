package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.SymbolParser;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wazi on 2015-09-19 019.
 */
public class SymbolTest {

	@Test
	public void testApply() throws Exception {
		SymbolParser parser = new SymbolParser();
		BaseExpression symbolExpression = parser.readExpression(new JTEX("#a + 14"));
		assertEquals(1, symbolExpression.symbolList().size());
		assertEquals("#a", symbolExpression.symbolList().get(0));
		assertEquals(15, symbolExpression.eval(new HashMap<String, Object>() {
			{
				put("#a", 215);
			}
		}));
	}
}