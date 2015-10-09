package wazi.jsontransformer.expression.operator;

import wazi.jsontransformer.expression.Token;

/**
 * Created by wazi on 9/25/15.
 */
public class Operator extends Token {

	public Op op;

	public Operator(Op op, int start, int end) {
		super(start, end);
		this.op = op;
	}

	public Operator(String op, int start, int end) {
		super(start, end);
		this.op = Op.parseOp(op);
	}

	public enum Op {
		//Numeric operators
		PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), DIV("div"), MOD("%"),

		//Relational operators
		LT("<"), GT(">"), LTE("<="), GTE(">="), EQ("="),

		//String operators
		CONCAT("."),

		//Bool operators
		AND("and"), OR("or"),

		//Bit operators
		;

		private String opString;

		Op(String opString) {
			this.opString = opString;
		}

		@Override
		public String toString() {
			return this.opString;
		}

		public static Op parseOp(String opString) {
			for (Op operator : values()) {
				if (operator.opString.equalsIgnoreCase(opString)) {
					return operator;
				}
			}
			throw new IllegalArgumentException("Not found operator with string: " + opString);
		}
	}
}
