package wazi.jsontransformer.expression.arithmetic;

import wazi.jsontransformer.exception.EvaluationException;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.helper.Num;
import wazi.jsontransformer.expression.operator.Operator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wazi on 10/9/15.
 */
public class TermExpression extends BaseExpression {

	public List<FactorExpression> factors;
	List<Operator> operators;
	boolean isPositive;

	public TermExpression() {
		this(-1, -1);
	}

	public TermExpression(int start, int end) {
		super(start, end);
		this.factors = new LinkedList<>();
		this.operators = new LinkedList<>();
		this.isPositive = true;
	}

	public void addFactor(Operator op, FactorExpression factor) {
		this.operators.add(op);
		this.factors.add(factor);
	}

	public void setIsPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		if (factors.size() == 0) {
			throw new EvaluationException("Factor number must be at least 1");
		}
		Num product = new Num(isPositive ? 1 : -1);

		for (int i = 0; i < operators.size(); i++) {
			Num factorValue = (Num) factors.get(i).eval(symbolMap);

			try {
				product = product.exe(operators.get(i).op, factorValue);
			} catch (IllegalArgumentException e) {
				throw new EvaluationException("Invalid arithmatic operator", e, i > 0 ? factors.get(i - 1).getEnd() : this.getStart(), factors.get(i).getStart());
			}
		}
		return product.get();
	}
}
