package wazi.parser.jsontransformer.expression;

import net.minidev.json.JSONObject;
import wazi.parser.jsontransformer.expression.function.J;

public class JsonPathExpression extends FunctionExpression {

	private boolean isEvaluated = false;

	public JsonPathExpression(int position) {
		super(J.class.getName(), "p", position);
		this.isEvaluated = false;
	}

	@Override
	public boolean isEvaluatable() {

		return this.isEvaluated;
	}

	public void setInputJson(JSONObject jsonObject) {

		// we no need to care about this argument's position because it comes from outside jtex expression
		addArgument(new LiteralExpression(jsonObject, -1));
	}
	
	@Override
	public Object val() throws Exception {
	
		Object val = super.val();
		this.isEvaluated = true;
		return val;
	}
}
