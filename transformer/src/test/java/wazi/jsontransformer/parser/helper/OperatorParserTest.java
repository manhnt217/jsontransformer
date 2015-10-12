package wazi.jsontransformer.parser.helper;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.operator.Operator;

import static org.junit.Assert.*;

/**
 * Created by wazi on 2015-10-11 011.
 */
public class OperatorParserTest {

	@Test
	public void testRead() throws Exception {
		OperatorParser operatorParser = new OperatorParser();
		Operator operator = operatorParser.read(new JTEX(">="));
		assertEquals(Operator.Op.GTE, operator.op);
	}
}