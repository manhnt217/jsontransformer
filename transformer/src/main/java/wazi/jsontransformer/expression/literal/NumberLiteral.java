package wazi.jsontransformer.expression.literal;

/**
 * Created by wazi on 10/1/15.
 */
public class NumberLiteral extends LiteralExpression {

    public NumberLiteral(Integer val, int start, int end) {
        super(val, start, end);
    }

    public NumberLiteral(Double val, int start, int end) {
        super(val, start, end);
    }
}
