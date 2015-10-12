package wazi.jsontransformer.expression.helper;

import wazi.jsontransformer.expression.operator.Operator;

/**
 * Created by wazi on 2015-10-07 007.
 * Wrapper class for double and integer
 */
public class Num {
	Double value;
	boolean isInteger;

	public Num(Object val) {
		assign(val);
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
		if (val instanceof Integer && Integer.MIN_VALUE <= (int) val && (int) val <= Integer.MAX_VALUE) {
			isInteger = true;
		} else if (val instanceof Long && Integer.MIN_VALUE <= (long) val && (long) val <= Integer.MAX_VALUE) {
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

	public Num apply(Operator.Op op, Num that) {
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

	public Boolean is(Operator.Op op, Num that) {
		switch (op) {
			case LT: return this.getDouble() < that.getDouble();
			case LTE: return this.getDouble() <= that.getDouble();
			case GT: return this.getDouble() > that.getDouble();
			case GTE: return this.getDouble() >= that.getDouble();
			case EQ: return this.getDouble() >= that.getDouble();
			case NEQ: return this.getDouble() != that.getDouble();
			default:
				throw new IllegalArgumentException("Expected an relation operator (<,>, <=, >=, =). Got " + op.toString());
		}
	}
}
