package wazi.jsontransformer.expression;

import wazi.jsontransformer.expression.BaseExpression;

import java.util.List;
import java.util.Map;

public class JsonPathExpression extends BaseExpression {

	public JsonPathExpression(int start, int end) {
		super(start, end);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public List<String> symbolList() {
		return null;
	}

}
