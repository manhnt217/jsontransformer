package wazi.jsontransformer.expression.parser.exception;

public class UnexpectedCharacterException extends ParserException {

	private static final long serialVersionUID = -5758303440108143307L;

	public UnexpectedCharacterException(int position, char c, String message) {
		super(position, message + " Unexpected character '" + c + "'");
	}
}
