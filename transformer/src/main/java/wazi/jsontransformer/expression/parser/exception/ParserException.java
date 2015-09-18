package wazi.jsontransformer.expression.parser.exception;

public class ParserException extends RuntimeException {

	private static final long serialVersionUID = -5787743504440870274L;

	protected int position;

	public ParserException(int position, String message, Throwable cause) {
		super(message + " at position " + position + ".", cause);
		this.position = position;
	}

	public ParserException(int position, String message) {
		this(position, message, null);
	}

	public int getPosition() {
		return position;
	}
}
