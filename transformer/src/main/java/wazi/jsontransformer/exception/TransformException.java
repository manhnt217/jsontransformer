package wazi.jsontransformer.exception;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class TransformException extends RuntimeException {
	public TransformException(String message) {
		super(message);
	}

	public TransformException(String message, Throwable cause) {
		super(message, cause);
	}
}
