package wazi.jsontransformer.expression.parser;

import wazi.jsontransformer.expression.Expression;
import wazi.jsontransformer.expression.jtex.JTEX;

/**
 * Interface for all parser class
 *
 * @author wazi
 */
public interface ExpressionParser {
	String packagePrefixString = "wazi.jsontransformer.expression.function.";

	Expression readExpression(JTEX jtex);
}

//	private FunctionParser functionParser;
//	private StringParser stringParser;
//	private NumberParser numberParser;
//	private BooleanParser booleanParser;
//	private NullParser nullParser;
//	private Object inputJSON;
//
//	public ExpressionParser() {
//
//		functionParser = new FunctionParser(this);
//		stringParser = new StringParser();
//		numberParser = new NumberParser();
//		booleanParser = new BooleanParser();
//		nullParser = new NullParser();
//	}
//
//	public Expression readExpression(JTEX jtex) {
//
//		switch (jtex.retrieveNext()) {
//			case '"': return new LiteralExpression(stringParser.readString(jtex), jtex.getBase1NextPosition());
//			case '0':
//			case '1':
//			case '2':
//			case '3':
//			case '4':
//			case '5':
//			case '6':
//			case '7':
//			case '8':
//			case '9':
//			case '+':
//			case '-': return new LiteralExpression(numberParser.readNumber(jtex), jtex.getBase1NextPosition());
//			case 't':
//			case 'f': return new LiteralExpression(booleanParser.readBool(jtex), jtex.getBase1NextPosition());
//			case 'n': return new LiteralExpression(nullParser.readNullValue(jtex), jtex.getBase1NextPosition());
//			default: {
//				if('A' <= jtex.retrieveNext() && jtex.retrieveNext() <= 'Z') return functionParser.readFunction(jtex);
//				else throw new UnexpectedCharacterException(jtex.getBase1NextPosition(), jtex.retrieveNext(), "Exception while reading expression.");
//			}
//		}
//	}
//
//	/*public Object getInputJSON() {
//
//		return inputJSON;
//	}
//
//
//	public void setInputJSON(Object inputJSON) {
//
//		this.inputJSON = inputJSON;
//	}*/
