package wazi.jsontransformer.expression;

import java.util.List;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class ArithmeticExpression extends BaseExpression {

	List<Term> terms;

	public ArithmeticExpression(int position) {
		super(null, position);
	}

	@Override
	public Object val() throws Exception {
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	public static class Term extends ArithmeticExpression {

		List<ArithmeticExpression> factors;

		public Term(int position, char sign) {
			super(position);
		}
	}

	public enum Sign {
		PLUS, MINUS, MULTIPLY, DIVIDE;

		@Override
		public String toString() {
			switch (this) {
				case PLUS: return "+";
				case MINUS: return "-";
				case MULTIPLY: return "-";
				case DIVIDE: return "-";
			}
		}
	}
}

