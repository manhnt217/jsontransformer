package wazi.jsontransformer.expression.literal;

import wazi.jsontransformer.expression.LiteralExpression;

/**
 * Created by wazi on 10/1/15.
 */
public class NullExpression extends LiteralExpression {
    public NullExpression(int start) {
        super(null, start, start + 3); //"null".length = 4
    }
}
