package wazi.jsontransformer.expression.relational;

import wazi.jsontransformer.exception.EvaluationException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.operator.Operator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 2015-10-10 010.
 */
public class RelationalExpression extends BaseExpression {

	public static final List<Operator.Op> OPERATORS = Arrays.asList(
			Operator.Op.EQ,
			Operator.Op.NEQ,
			Operator.Op.GT,
			Operator.Op.LT,
			Operator.Op.GTE,
			Operator.Op.LTE
	);
	private List<BaseExpression> exps;
	private List<Operator> operators;

	public RelationalExpression() {
		super(-1, -1);
		this.exps = new LinkedList<>();
		this.operators = new LinkedList<>();
	}

	public void addExpression(Operator operator, BaseExpression expression) {
		this.operators.add(operator);
		this.exps.add(expression);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		boolean result = true;
		for (int i = 0; i < operators.size(); i++) {
			Operator operator = operators.get(i);
			if (!OPERATORS.contains(operator.op)) {
				throw new EvaluationException("Expected a relational operator (>, >=, <, <=, =, <>). Got " + operator.op.toString());
			}
			result = result && compare(exps.get(i).eval(symbolMap), exps.get(i + 1).eval(symbolMap), operator);
		}
		return result;
	}

	private boolean compare(Object val1, Object val2, Operator operator) {

		Operator.Op op = operator.op;

		if (val1 == null) {
			return compareWithNull(val2, op);
		}
		if (val2 == null) {
			return compareWithNull(val1, op);
		}
		if (val1.getClass().equals(val2.getClass())) {//boolean, string, integer, double are all implemented Comparable
			if (val1 instanceof Comparable) {
				int compare = ((Comparable) val1).compareTo(val2);
				switch (op) {
					case EQ:
						return compare == 0;
					case NEQ:
						return compare != 0;
					case LT:
						return compare < 0;
					case GTE:
						return compare >= 0;
					case GT:
						return compare > 0;
					case LTE:
						return compare <= 0;
					default:
						return false;
				}
			} else {
				if (op == Operator.Op.EQ || op == Operator.Op.NEQ/*|| op == Operator.Op.LTE || op == Operator.Op.GTE*/) {
					return (op == Operator.Op.EQ) == (val1.equals(val2));
				} else {
					throw new EvaluationException("Cannot compare " + op.toString() + " between two object of class " + val1.getClass().getName(), operator.getStart(), operator.getEnd());
				}
			}
		}
		try {
			return compareNum(val1, val2, operator);
		} catch (IllegalArgumentException e) {
			//input is not a number
		}
		return compareString(val1.toString(), val2.toString(), operator);
	}

	private boolean compareString(String s1, String s2, Operator operator) {
		int compare = s1.compareTo(s2);
		switch (operator.op) {
			case EQ:
				return compare == 0;
			case NEQ:
				return compare != 0;
			case LT:
				return compare < 0;
			case GTE:
				return compare >= 0;
			case GT:
				return compare > 0;
			case LTE:
				return compare <= 0;
			default:
				throw new EvaluationException("Expected a relational operator (>, <, >=, <=, =, <>). Got " + operator.toString(), operator.getStart(), operator.getEnd());
		}
	}

	private boolean compareNum(Object val1, Object val2, Operator operator) {
		Num n1, n2;
		if (val1 instanceof Num) n1 = (Num) val1;
		else n1 = new Num(val1);

		if (val2 instanceof Num) n2 = (Num) val2;
		else n2 = new Num(val2);

		try {
			return n1.is(operator.op, n2);
		} catch (IllegalArgumentException e) {
			throw new EvaluationException(e.getMessage(), operator.getStart(), operator.getEnd());
		}
	}

	private boolean compareWithNull(Object val, Operator.Op op) {
		if (val == null) {
			return op == Operator.Op.EQ || op == Operator.Op.LTE || op == Operator.Op.GTE;
		} else return "null".equalsIgnoreCase(val.toString());
	}

	public void addFirstExpression(BaseExpression expression) {
		this.exps.clear();
		this.exps.add(expression);
	}
}
