package wazi.jsontransformer.expression;

import java.util.List;
import java.util.Map;

public class JsonPathExpression extends BaseExpression {

	public JsonPathExpression(int start, int end) {
		super(start, end);
	}

	@Override
	public Object eval(Map<String, Object> symbolMap) {
		throw new UnsupportedOperationException("Dont use this method, use apply instead");
	}

	@Override
	public List<String> symbolList() {
		return null;
	}

}
