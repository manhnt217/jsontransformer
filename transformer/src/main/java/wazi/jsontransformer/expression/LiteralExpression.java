package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

public class LiteralExpression extends BaseExpression {

	protected final Object val;

	public LiteralExpression(Object val, int start, int end) {
		super(start, end);
		this.val = val;
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		return val;
	}

	/**
	 * Literal does not contain symbol
	 * @return
	 */
	@Override
	public List<String> symbolList() {
		return null;
	}

	public Object val() {
		return val;
	}
}
