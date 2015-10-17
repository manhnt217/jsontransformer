package wazi.jsontransformer.expression.logical;

import wazi.jsontransformer.exception.EvaluationException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.operator.Operator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 2015-10-11 011.
 */
public class LogicalExpression extends BaseExpression {

	public static final List<Operator.Op> OPERATORS = Arrays.asList(
			Operator.Op.AND,
			Operator.Op.OR
	);

	private List<BaseExpression> boolExpressions;
	private List<Operator> operators;

	public LogicalExpression() {
		super(-1, -1);
		boolExpressions = new LinkedList<>();
		operators = new LinkedList<>();
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		boolean result = false;

		for (int i = 0; i < operators.size(); i++) {
			BaseExpression exp = boolExpressions.get(i);
			Object val = exp.eval(symbolMap);
			boolean b = convertToBoolean(val);
			if (operators.get(i).op == Operator.Op.AND) {
				result = result && b;
			} else if (operators.get(i).op == Operator.Op.OR) {
				result = result || b;
			}
		}

		return result;
	}

	private boolean convertToBoolean(Object val) {
		if (val == null || "null".equals(val.toString())) return false;
		if (val instanceof Boolean) return (boolean)val;
		if (val instanceof Number) {
			Number n = (Number)val;
			return n.doubleValue() != 0; //n == 0 means false, else true
		} else if("false".equals(val.toString())) {
			return false;
		} else {
			return true;
		}
	}

	public void addExpression(Operator op, BaseExpression boolExpression) {
		operators.add(op);
		boolExpressions.add(boolExpression);
	}
}
