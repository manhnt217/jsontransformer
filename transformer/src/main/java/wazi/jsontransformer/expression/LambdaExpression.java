package wazi.jsontransformer.expression;

import java.util.function.Function;

/**
 * Created by wazi on 2015-09-19 019.
 */
class LambdaExpression extends BaseExpression implements Function<Expression, Expression> {

	public LambdaExpression(int position) {
		super(null, position);
	}

	@Override
	public Expression apply(Expression expression) {
		Expression exp = null;
		//code will go here
		this.val = exp;
		return exp;
	}

	@Override
	public Object val() throws Exception {
		throw new UnsupportedOperationException("Use apply method instead");
	}
}
