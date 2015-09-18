package wazi.jsontransformer.expression.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.Expression;
import wazi.jsontransformer.expression.LiteralExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.expression.parser.exception.UnexpectedCharacterException;
import wazi.jsontransformer.expression.parser.literal.BooleanParser;
import wazi.jsontransformer.expression.parser.literal.NullParser;
import wazi.jsontransformer.expression.parser.literal.NumberParser;
import wazi.jsontransformer.expression.parser.literal.StringParser;

/**
 * Expression evaluation class
 *
 * @author wazi
 *
 */
public class ExpressionParser {

	private FunctionParser functionParser;
	private StringParser stringParser;
	private NumberParser numberParser;
	private BooleanParser booleanParser;
	private NullParser nullParser;
	private Object inputJSON;
	String packagePrefixString = "wazi.parser.jsontransformer.expression.function.";

	public ExpressionParser() {

		functionParser = new FunctionParser(this);
		stringParser = new StringParser();
		numberParser = new NumberParser();
		booleanParser = new BooleanParser();
		nullParser = new NullParser();
	}

	public Expression readExpression(JTEX jtex) {

		switch (jtex.retrieveNext()) {
			case '"': return new LiteralExpression(stringParser.readString(jtex), jtex.getNextPosition());
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '+':
			case '-': return new LiteralExpression(numberParser.readNumber(jtex), jtex.getNextPosition());
			case 't':
			case 'f': return new LiteralExpression(booleanParser.readBool(jtex), jtex.getNextPosition());
			case 'n': return new LiteralExpression(nullParser.readNullValue(jtex), jtex.getNextPosition());
			default: {
				if('A' <= jtex.retrieveNext() && jtex.retrieveNext() <= 'Z') return functionParser.readFunction(jtex);
				else throw new UnexpectedCharacterException(jtex.getNextPosition(), jtex.retrieveNext(), "Exception while reading expression.");
			}
		}
	}

	public Object getInputJSON() {

		return inputJSON;
	}


	public void setInputJSON(Object inputJSON) {

		this.inputJSON = inputJSON;
	}
}