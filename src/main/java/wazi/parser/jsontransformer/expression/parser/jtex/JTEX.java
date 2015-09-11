package wazi.parser.jsontransformer.expression.parser.jtex;

import wazi.parser.jsontransformer.expression.parser.exception.EndOfJtexException;

public class JTEX {

	private String ex;
	private int nextPosition;
	private Character nextChar;
	private Character currentChar;

	public JTEX(String jtex) {
		this.ex = jtex;
		this.nextPosition = 0;
	}

	public Character next() throws EndOfJtexException {

		if(!hasNext()) {
			throw new EndOfJtexException(nextPosition + 1);
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
		
		if(!hasNext()) {
			throw new EndOfJtexException(nextPosition + 1);
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
	
	/**
	 * @return based 1 position number
	 */
	public int getPosition() {

		return nextPosition;
	}
}
