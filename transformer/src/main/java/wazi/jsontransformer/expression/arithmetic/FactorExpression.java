package wazi.jsontransformer.expression.arithmetic;

import wazi.jsontransformer.exception.EvaluationException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.operator.Operator;

import java.util.Map;

/**
 * Created by wazi on 10/9/15.
 */
public class FactorExpression extends BaseExpression {

	private BaseExpression baseExpression;
	private boolean isPositive;

	public FactorExpression(int start, int end) {
		super(start, end);
		isPositive = true;
	}

	public FactorExpression() {
		this(-1, -1);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		Object val = baseExpression.eval(symbolMap);
		Num n;
		if (val instanceof Num) {
			n = (Num) val;
		} else {
			try {
				n = new Num(val);
			} catch (IllegalArgumentException e) {
				throw new EvaluationException(e.getMessage(), e, baseExpression.getStart(), baseExpression.getEnd());
			}
		}
		return n.exe(Operator.Op.MULTIPLY, new Num(isPositive ? 1 : -1));
	}

	public void setBaseExpression(BaseExpression baseExpression) {
		this.baseExpression = baseExpression;
	}

	public void setIsPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}
}
