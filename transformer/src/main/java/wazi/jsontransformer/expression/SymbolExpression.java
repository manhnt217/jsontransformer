package wazi.jsontransformer.expression;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Describe symbol in expression. For example, #abc, #x
 * Created by wazi on 2015-09-19 019.
 */
public class SymbolExpression extends BaseExpression {

	private String symbol;

	public SymbolExpression(String symbol, int start, int end) {
		super(start, end);
		this.symbol = symbol;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		return symbolMap.get(this.symbol);
	}

	@Override
	public List<String> symbolList() {
		return Arrays.asList(this.symbol);
	}
}
