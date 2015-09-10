package wazi.parser.jsontransformer.expression.parser;

public class JTEX {

	private String ex;
	private int nextPosition;
	
	public JTEX(String jtex) {
		this.ex = jtex;
		this.nextPosition = 0;
	}
	
	public Character readNext() {
		
		Character nextChar = getNext();
		nextPosition ++;
		return nextChar;
	}

	public Character getNext() {
		
		if (nextPosition == ex.length()) return null;
		return ex.charAt(nextPosition);
	}
	
	public boolean hasNext() {
		return nextPosition < ex.length();
	}
}
