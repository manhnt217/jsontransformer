package wazi.jsontransformer.expression;

import wazi.jsontransformer.exception.EvaluationException;

import java.util.Map;

/**
 * Describe symbol in expression. For example, #abc, #x
 * Created by wazi on 2015-09-19 019.
 */
public class SymbolLiteral extends BaseExpression {

	private String symbol;

	public SymbolLiteral(String symbol, int start, int end) {
		super(start, end);
		this.symbol = symbol;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		if (!symbolMap.containsKey(this.symbol)) {
			throw new EvaluationException("Symbol value not found", this.start, this.end);
		}
		return symbolMap.get(this.symbol);
	}
}
