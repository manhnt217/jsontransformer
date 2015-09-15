package wazi.parser.jsontransformer.expression.parser.exception;


public class EndOfJtexException extends ParserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725395812135014699L;
	
	public EndOfJtexException(int position) {
		super(position, "Jtex expression ends. No more character to read");
	}
}
