package wazi.parser.jsontransformer.expression;

import net.minidev.json.JSONObject;
import wazi.parser.jsontransformer.expression.function.J;

public class JsonPathExpression extends FunctionExpression {

	public JsonPathExpression(int position) {
		super(J.class.getName(), "p", position);
	}

	public void setInputJson(JSONObject jsonObject) {

		// we no need to care about this argument's position because it comes from outside jtex expression
		addArgument(new BaseExpression(jsonObject, -1));
	}
	
	@Override
	public Object val() throws Exception {
	
		return super.val();
	}
}
