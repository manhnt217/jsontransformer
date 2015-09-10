package wazi.parser.jsontransformer.expression.parser.jtex;


public class EndOfJtexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725395812135014699L;
	
	public EndOfJtexException() {
		super("Jtex expression ends. No more character to read");
	}
}
