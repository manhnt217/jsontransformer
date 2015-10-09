package wazi.jsontransformer.parser.helper;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.parser.FunctionParser;
import wazi.jsontransformer.parser.arithmetic.ArithmeticExpressionParser;
import wazi.jsontransformer.parser.literal.*;

/**
 * Created by wazi on 10/9/15.
 */
public class ExpressionParser extends MultiChoiceParser<BaseExpression> {

	public final NullExpressionParser nullExpressionParser;
	public final BooleanExpressionParser booleanExpressionParser;
	//public final NumberExpressionParser numberExpressionParser;
	public final StringExpressionParser stringExpressionParser;
	public final SymbolParser symbolParser;
	public final ArithmeticExpressionParser arithmeticExpressionParser;
	public final FunctionParser functionParser;

	public ExpressionParser() {
		super();
		nullExpressionParser = new NullExpressionParser();
		booleanExpressionParser = new BooleanExpressionParser();
		//numberExpressionParser = new NumberExpressionParser();
		stringExpressionParser = new StringExpressionParser();
		symbolParser = new SymbolParser();
		arithmeticExpressionParser = new ArithmeticExpressionParser();
		functionParser = new FunctionParser(this);

		this.addParser(nullExpressionParser);
		this.addParser(booleanExpressionParser);
		//this.addParser(numberExpressionParser);
		this.addParser(stringExpressionParser);
		this.addParser(symbolParser);
		this.addParser(arithmeticExpressionParser);
		this.addParser(functionParser);
	}
}
