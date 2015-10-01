package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class ArithmeticExpression extends BaseExpression {

	List<TermExpression> terms;
	Operator currentOp;

	public ArithmeticExpression(int start, int end) {
		super(start, end);
		currentOp = Operator.PLUS;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public List<String> symbolList() {
		return null;
	}

	static class TermExpression extends ArithmeticExpression {

		List<BaseExpression> factors;
		boolean isNegative;

		public TermExpression(int start, int end) {
			super(start, end);
		}
	}
}

