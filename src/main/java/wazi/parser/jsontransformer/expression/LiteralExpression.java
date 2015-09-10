package wazi.parser.jsontransformer.expression;

/**
 * Express literal values in jtex, include String, Number, Boolean, Null
 * @author wazi
 *
 */
public class LiteralExpression implements Expression {
	
	protected Object val;
	private int position;
	
	
	public LiteralExpression(Object val, int position) {
		this.val = val;
		this.position = position;
	}

	@Override
	public boolean isEvaluatable() {

		return true;
	}

	@Override
	public Object val() {

		return this.val;
	}

	@Override
	public int getPostion() {
	
		return this.position;
	}
}
