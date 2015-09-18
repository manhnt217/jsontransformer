package wazi.jsontransformer.expression.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.EndOfJtexException;
import wazi.jsontransformer.expression.parser.exception.ParserException;

public class NumberParser {

	private StringBuilder numberBuilder;

	public Object readNumber(JTEX jtex) {
		numberBuilder = new StringBuilder();
		boolean isInteger = true;
		EndOfJtexException endOfJtexException = null;
		try {
			// read integer part
			readInteger(jtex);

			if (jtex.retrieveNext() == '.') {
				numberBuilder.append(jtex.next());
				isInteger = false;
				// read fraction part
				readNumberSeq(jtex);
			}

			if (jtex.retrieveNext() == 'E' || jtex.retrieveNext() == 'e') {
				numberBuilder.append(jtex.next());
				isInteger = false;
				readInteger(jtex);
			}
		} catch (EndOfJtexException e) {
			endOfJtexException = e;
		}
		
		try {
			if (isInteger) {
				return Integer.parseInt(numberBuilder.toString());
			} else {
				return Double.parseDouble(numberBuilder.toString());
			}
		} catch (NumberFormatException ex) {
			if(endOfJtexException != null) {
				throw new ParserException(endOfJtexException.getPosition(), "Exception while reading number", endOfJtexException);
			} else {
				throw new ParserException(jtex.getPosition(), "Number format exception");
			}
		}
	}

	private void readInteger(JTEX jtex) throws EndOfJtexException {
		if ('+' == jtex.retrieveNext() || '-' == jtex.retrieveNext()) {
			numberBuilder.append(jtex.next());
		}
		readNumberSeq(jtex);
	}

	private void readNumberSeq(JTEX jtex) throws EndOfJtexException {
		while (jtex.hasNext() && '0' <= jtex.retrieveNext() && jtex.retrieveNext() <= '9') {
			numberBuilder.append(jtex.next());
		}
	}
}
