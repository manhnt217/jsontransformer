package wazi.jsontransformer.expression.jtex;

import wazi.jsontransformer.expression.parser.exception.EndOfJtexException;

public class JTEX {

	private String ex;
	private int nextPosition;
	private Character nextChar;
	private Character currentChar;

	public JTEX(String jtex) {
		this(jtex, 0);
	}

	public JTEX(String jtex, int nextPosition) {
		this.ex = jtex;
		this.nextPosition = nextPosition;
	}

	public Character next() throws EndOfJtexException {

		if (!hasNext()) {
			throw new EndOfJtexException(nextPosition);
		}

		if (nextChar == null) {
			retrieveNext();
		}

		Character result = nextChar;
		currentChar = nextChar;
		nextChar = null; //reset next char to null
		nextPosition++;
		return result;
	}

	public Character retrieveNext() throws EndOfJtexException {

		if (!hasNext()) {
			throw new EndOfJtexException(nextPosition);
		}

		if (nextChar == null) {
			nextChar = ex.charAt(nextPosition);//store next char for better performance
		}

		return nextChar;
	}

	public Character current() {
		return currentChar;
	}

	public boolean hasNext() {

		return nextPosition < ex.length();
	}

	public void skipBlank() {
		while (retrieveNext() == ' ' || retrieveNext() == '\t') {
			next();
		}
	}

	public void setNextPosition(int position) {
		this.nextPosition = position;
		this.nextChar = null;
		this.currentChar = null;
	}

	public int getNextPosition() {

		return nextPosition;
	}

	public String getString() {
		return this.ex;
	}
}
