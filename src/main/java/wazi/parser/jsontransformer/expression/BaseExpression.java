package wazi.parser.jsontransformer.expression;

/**
 * Express literal values in jtex, include String, Number, Boolean, Null
 * @author wazi
 *
 */
public class BaseExpression implements Expression {
	
	protected Object val;
	private int position;
	
	
	public BaseExpression(Object val, int position) {
		this.val = val;
		this.position = position;
	}

	@Override
	public Object val() throws Exception {

		return this.val;
	}

	@Override
	public int getPosition() {
	
		return this.position;
	}
}
