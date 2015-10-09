package wazi.jsontransformer.expression;

/**
 * Created by wazi on 10/8/15.
 */
public class Token {

	protected int start;
	protected int end;

	public Token(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
