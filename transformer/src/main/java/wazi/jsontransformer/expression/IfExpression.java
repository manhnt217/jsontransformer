package wazi.jsontransformer.expression;

import wazi.jsontransformer.expression.logical.LogicalExpression;

import java.util.Map;

/**
 * Created by wazi on 10/17/15.
 */
public class IfExpression extends BaseExpression {

	BaseExpression ifClause;
	BaseExpression thenClause;
	BaseExpression elseClause;

	public IfExpression() {
		super(-1, -1);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		Object val = ifClause.eval(symbolMap);
		boolean ifValue;
		if (val instanceof Boolean) {
			ifValue = (boolean) val;
		} else {
			ifValue = LogicalExpression.convertToBoolean(val);
		}
		if (ifValue) {
			return thenClause.eval(symbolMap);
		} else {
			return elseClause.eval(symbolMap);
		}
	}

	public void setIfClause(BaseExpression ifClause) {
		this.ifClause = ifClause;
	}

	public void setThenClause(BaseExpression thenClause) {
		this.thenClause = thenClause;
	}

	public void setElseClause(BaseExpression elseClause) {
		this.elseClause = elseClause;
	}
}
