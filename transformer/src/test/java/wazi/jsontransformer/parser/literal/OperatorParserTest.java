package wazi.jsontransformer.parser.literal;

import org.junit.Test;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.operator.Operator;

import static org.junit.Assert.*;

/**
 * Created by wazi on 10/8/15.
 */
public class OperatorParserTest {
	@Test
	public void testReadOperator() {
		OperatorParser operatorParser = new OperatorParser();
		JTEX jtex = new JTEX("div");
		Operator op = operatorParser.read(jtex);
		assertEquals(3, jtex.getNextPosition());
		assertEquals(Operator.Op.DIV, op.op);
		assertEquals(0, op.getStart());
		assertEquals(2, op.getEnd());
	}
}