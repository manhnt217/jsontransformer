package wazi.jsontransformer.parser.literal;

import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.literal.NumberExpression;
import wazi.jsontransformer.parser.TokenParser;
import wazi.jsontransformer.exception.parser.EndOfJtexException;
import wazi.jsontransformer.exception.parser.ParserException;

public class NumberExpressionParser implements TokenParser<NumberExpression> {

	private StringBuilder numberBuilder;

	public NumberExpression read(JTEX jtex) {
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
				return new NumberExpression(Integer.parseInt(numberBuilder.toString()), jtex.getNextPosition() - numberBuilder.length(), jtex.getNextPosition() - 1);
			} else {
				return new NumberExpression(Double.parseDouble(numberBuilder.toString()), jtex.getNextPosition() - numberBuilder.length(), jtex.getNextPosition() - 1);
			}
		} catch (NumberFormatException ex) {
			if(endOfJtexException != null) {
				throw new ParserException(endOfJtexException.getPosition(), "Exception while reading number", endOfJtexException);
			} else {
				throw new ParserException(jtex.getNextPosition(), "Number format exception");
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
