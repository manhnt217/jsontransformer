package wazi.jsontransformer.parser;

import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.parser.arithmetic.ArithmeticExpressionParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;
import wazi.jsontransformer.parser.literal.*;
import wazi.jsontransformer.parser.logical.relational.RelationalExpressionParser;

/**
 * Created by wazi on 10/9/15.
 */
public class ExpressionParser extends MultiChoiceParser<BaseExpression> {

	public final NullLiteralParser nullLiteralParser;
	public final BooleanLiteralParser booleanLiteralParser;
	public final StringLiteralParser stringLiteralParser;
	public final SymbolParser symbolParser;
	public final ArithmeticExpressionParser arithmeticExpressionParser;
	public final FunctionExpressionParser functionExpressionParser;
	public final RelationalExpressionParser relationalExpressionParser;

	public ExpressionParser() {
		super();
		nullLiteralParser = new NullLiteralParser();
		booleanLiteralParser = new BooleanLiteralParser();
		stringLiteralParser = new StringLiteralParser();
		symbolParser = new SymbolParser();
		arithmeticExpressionParser = new ArithmeticExpressionParser();
		functionExpressionParser = new FunctionExpressionParser(this);
		relationalExpressionParser = new RelationalExpressionParser(this);

		this.addParser(nullLiteralParser);
		this.addParser(booleanLiteralParser);
		//this.addParser(numberExpressionParser);
		this.addParser(stringLiteralParser);
		this.addParser(symbolParser);
		this.addParser(arithmeticExpressionParser);
		this.addParser(functionExpressionParser);
		this.addParser(relationalExpressionParser);
	}
}
