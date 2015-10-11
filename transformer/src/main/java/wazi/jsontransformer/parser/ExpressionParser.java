package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.parser.FunctionParser;
import wazi.jsontransformer.parser.arithmetic.ArithmeticExpressionParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.*;
import wazi.jsontransformer.parser.relational.RelationalExpressionParser;

/**
 * Created by wazi on 10/9/15.
 */
public class ExpressionParser extends MultiChoiceParser<BaseExpression> {

	public final NullExpressionParser nullExpressionParser;
	public final BooleanExpressionParser booleanExpressionParser;
	public final StringExpressionParser stringExpressionParser;
	public final SymbolParser symbolParser;
	public final ArithmeticExpressionParser arithmeticExpressionParser;
	public final FunctionParser functionParser;
	public final RelationalExpressionParser relationalExpressionParser;

	public ExpressionParser() {
		super();
		nullExpressionParser = new NullExpressionParser();
		booleanExpressionParser = new BooleanExpressionParser();
		stringExpressionParser = new StringExpressionParser();
		symbolParser = new SymbolParser();
		arithmeticExpressionParser = new ArithmeticExpressionParser();
		functionParser = new FunctionParser(this);
		relationalExpressionParser = new RelationalExpressionParser(this);

		this.addParser(nullExpressionParser);
		this.addParser(booleanExpressionParser);
		//this.addParser(numberExpressionParser);
		this.addParser(stringExpressionParser);
		this.addParser(symbolParser);
		this.addParser(arithmeticExpressionParser);
		this.addParser(functionParser);
		this.addParser(relationalExpressionParser);
	}
}
