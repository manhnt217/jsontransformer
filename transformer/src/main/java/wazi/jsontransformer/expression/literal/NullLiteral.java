package wazi.jsontransformer.expression.literal;

/**
 * Created by wazi on 10/1/15.
 */
public class NullLiteral extends LiteralExpression {
    public NullLiteral(int start) {
        super(null, start, start + 3); //"null".length = 4
    }
}
