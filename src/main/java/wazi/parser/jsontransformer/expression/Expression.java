package wazi.parser.jsontransformer.expression;

public interface Expression {
	
	//	/**
	//	 * @return <code>true</code> if this expression does not contain Json Path selector<br>
	//	 * 			Otherwise return <code>false</code>.
	//	 */
	//	public boolean isEvaluatable();
	
	/**
	 * @return the value of expression if <code>Expression.isEvaluatable() == true</code><br>
	 * 			Otherwise return <code>null</code>.
	 * @throws Exception 
	 */
	public Object val() throws Exception;
	
	/**
	 * @return position of this expression in entire jtex expression for tracing purpose
	 */
	public int getPosition();
}
