package wazi.jsontransformer.expression.helper;

import wazi.jsontransformer.expression.operator.Operator;

/**
 * Created by wazi on 2015-10-07 007.
 */
public class Num {
	Double value;
	boolean isInteger;

	public Num(Object val) {
		assign(val);
	}

	public Num() {
		this(0);
	}

	public Object get() {
		if (isInteger) return value.intValue();
		else return value;
	}

	public int getInt() {
		return value.intValue();
	}

	public double getDouble() {
		return value;
	}

	public void assign(Object val) {
		if ((val instanceof Integer || val instanceof Long) && Integer.MIN_VALUE <= (int) val && (int) val <= Integer.MAX_VALUE) {
			isInteger = true;
		} else {
			try {
				Integer.parseInt(val.toString());
				isInteger = true;
			} catch (NumberFormatException e) {
				//not an integer
			}
		}
		try {
			this.value = Double.valueOf("" + val);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Val must be a number. Got " + val.getClass().getName());
		}
	}

	public Num exe(Operator.Op op, Num that) {
		if (this.isInteger && that.isInteger) {
			switch (op) {
				case PLUS:
					return new Num(this.getInt() + that.getInt());
				case MINUS:
					return new Num(this.getInt() - that.getInt());
				case MULTIPLY:
					return new Num(this.getInt() * that.getInt());
				case DIVIDE: {
					if (this.getInt() % that.getInt() == 0) return new Num(this.getInt() / that.getInt());
					else return new Num(this.getDouble() / that.getInt());
				}
				case DIV:
					return new Num(this.getInt() / that.getInt());
				case MOD:
					return new Num(this.getInt() % that.getInt());
				default:
					throw new IllegalArgumentException("Expected an arithmetic operator (+, -, *, /, div, %). Got " + op.toString());
			}
		} else {
			switch (op) {
				case PLUS:
					return new Num(this.getDouble() + that.getDouble());
				case MINUS:
					return new Num(this.getDouble() - that.getDouble());
				case MULTIPLY:
					return new Num(this.getDouble() * that.getDouble());
				case DIVIDE:
					return new Num(this.getDouble() / that.getDouble());
				case DIV:
					return new Num(this.getDouble() / that.getDouble());
				case MOD:
					return new Num(this.getDouble() % that.getDouble());
				default:
					throw new IllegalArgumentException("Expected an arithmetic operator (+, -, *, /, div, %). Got " + op.toString());
			}
		}
	}
}
