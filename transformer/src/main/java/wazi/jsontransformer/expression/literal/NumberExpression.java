package wazi.jsontransformer.expression.literal;

import wazi.jsontransformer.expression.LiteralExpression;

/**
 * Created by wazi on 10/1/15.
 */
public class NumberExpression extends LiteralExpression {

    public NumberExpression(Integer val, int start, int end) {
        super(val, start, end);
    }

    public NumberExpression(Double val, int start, int end) {
        super(val, start, end);
    }

    public int intValue() {
        if (val instanceof Double) return ((Double) val).intValue();
        return (int) val;
    }
}
