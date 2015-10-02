package wazi.jsontransformer.expression.literal;

import wazi.jsontransformer.expression.LiteralExpression;

/**
 * Created by wazi on 10/1/15.
 */
public class BooleanExpression extends LiteralExpression {
	public BooleanExpression(Boolean val, int start) {
		super(val, start, val ? start + 3 : start + 4);
	}
}
