package wazi.jsontransformer.expression;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Express literal values in jtex, include String, Number, Boolean, Null
 *
 * @author wazi
 */
public abstract class BaseExpression extends Token {

    public BaseExpression(int start, int end) {
        super(start, end);
    }

    /**
     * @param symbolMap
     * @return the value of expression while applying values to symbols in this expression.
     * @throws RuntimeException if evaluation fails
     */
    public abstract Object eval(Map<String, Object> symbolMap);
}
