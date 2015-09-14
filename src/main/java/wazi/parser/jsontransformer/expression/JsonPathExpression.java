package wazi.parser.jsontransformer.expression;

import wazi.parser.jsontransformer.expression.function.J;

public class JsonPathExpression extends FunctionExpression {

	public JsonPathExpression(int position) {
		super(J.class.getName(), "p", position);
	}

	public void setInputJson(Object jsonObject) {

		// we no need to care about this argument's position because it comes from outside jtex expression
		addArgument(new BaseExpression(jsonObject, -1));
	}
	
	@Override
	public Object val() throws Exception {
	
		return super.val();
	}
}
