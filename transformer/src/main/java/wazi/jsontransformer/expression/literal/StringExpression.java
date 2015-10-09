package wazi.jsontransformer.expression.literal;

import wazi.jsontransformer.expression.LiteralExpression;

/**
 * Created by wazi on 9/23/15.
 */
public class StringExpression extends LiteralExpression {
    public StringExpression(String val, int start, int end) {
        super(val, start, end);
    }
}

