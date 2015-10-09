package wazi.jsontransformer.exception;

/**
 * Created by wazi on 2015-09-20 020.
 */
public class EvaluationException extends RuntimeException {

    private int start;
    private int end;

    public EvaluationException(String message) {
        super(message);
    }

    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluationException(String message, Throwable cause, int startPosition, int endPosition) {
        this("Exception while evaluating expression, start at " + startPosition + ", end at " + endPosition + ". " + message, cause);
        this.start = startPosition;
        this.end = endPosition;
    }

    public EvaluationException(String message, int startPosition, int endPosition) {
        this(message, null, startPosition, endPosition);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
