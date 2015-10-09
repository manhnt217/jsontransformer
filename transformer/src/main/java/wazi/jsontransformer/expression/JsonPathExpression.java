package wazi.jsontransformer.expression;

import java.util.Map;

public class JsonPathExpression extends BaseExpression {

	public JsonPathExpression(int start, int end) {
		super(start, end);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
