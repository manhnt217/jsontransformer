package wazi.parser.jsontransformer.expression.parser.jtex;

public class JTEX {

	private String ex;
	private int nextPosition;
	private Character nextChar;

	public JTEX(String jtex) {
		this.ex = jtex;
		this.nextPosition = 0;
	}

	public Character next() throws EndOfJtexException {

		if(!hasNext()) {
			throw new EndOfJtexException();
		}

		if (nextChar == null) {
			retrieveNext();
		}

		Character result = nextChar;
		nextChar = null; //reset next char to null
		nextPosition++;
		return result;
	}

	public Character retrieveNext() {

		if (nextChar == null) {
			if (nextPosition == ex.length()) return null;
			nextChar = ex.charAt(nextPosition);//store next char for better performance
		}
		return nextChar;
	}

	public boolean hasNext() {

		return nextPosition < ex.length();
	}
	
	public int getPosition() {

		return nextPosition - 1;
	}
}
