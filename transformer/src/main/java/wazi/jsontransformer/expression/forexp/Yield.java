package wazi.jsontransformer.expression.forexp;

import wazi.jsontransformer.expression.BaseExpression;

import java.util.Map;

/**
 * Created by wazi on 10/19/15.
 */
public class Yield extends BaseExpression {

	private BaseExpression baseExpression;

	public Yield() {
		super(-1, -1);
	}

	public void setBaseExpression(BaseExpression baseExpression) {
		this.baseExpression = baseExpression;
	}

	public BaseExpression getBaseExpression() {
		return baseExpression;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		return this;//simply return this yield expression to for expression to process
	}
}
