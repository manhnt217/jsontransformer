package wazi.jsontransformer.expression.literal;

/**
 * Created by wazi on 10/1/15.
 */
public class BooleanLiteral extends LiteralExpression {
	public BooleanLiteral(Boolean val, int start) {
		super(val, start, val ? start + 3 : start + 4);
	}
}
